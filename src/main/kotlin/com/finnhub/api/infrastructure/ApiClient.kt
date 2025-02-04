package com.finnhub.api.infrastructure

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.JsonObject
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.net.URLConnection
import java.time.*
import java.util.*

open class ApiClient(val baseUrl: String) {
    companion object {
        protected const val ContentType = "Content-Type"
        protected const val Accept = "Accept"
        protected const val JsonMediaType = "application/json"
        protected const val FormDataMediaType = "multipart/form-data"
        protected const val FormUrlEncMediaType = "application/x-www-form-urlencoded"
        protected const val XmlMediaType = "application/xml"

        val apiKey: MutableMap<String, String> = mutableMapOf()
        val apiKeyPrefix: MutableMap<String, String> = mutableMapOf()

        @JvmStatic
        val client: OkHttpClient by lazy {
            builder.build()
        }

        @JvmStatic
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
    }

    /**
     * Guess Content-Type header from the given file (defaults to "application/octet-stream").
     *
     * @param file The given file
     * @return The guessed Content-Type
     */
    protected fun guessContentTypeFromFile(file: File): String {
        val contentType = URLConnection.guessContentTypeFromName(file.name)
        return contentType ?: "application/octet-stream"
    }

    protected inline fun <reified T> requestBody(content: T, mediaType: String = JsonMediaType): RequestBody =
        when {
            content is File -> content.asRequestBody(
                mediaType.toMediaTypeOrNull()
            )
            mediaType == FormDataMediaType -> {
                MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .apply {
                        // content's type *must* be Map<String, Any?>
                        @Suppress("UNCHECKED_CAST")
                        (content as Map<String, Any?>).forEach { (key, value) ->
                            if (value is File) {
                                val partHeaders = Headers.headersOf(
                                    "Content-Disposition",
                                    "form-data; name=\"$key\"; filename=\"${value.name}\""
                                )
                                val fileMediaType = guessContentTypeFromFile(value).toMediaTypeOrNull()
                                addPart(partHeaders, value.asRequestBody(fileMediaType))
                            } else {
                                val partHeaders = Headers.headersOf(
                                    "Content-Disposition",
                                    "form-data; name=\"$key\""
                                )
                                addPart(
                                    partHeaders,
                                    parameterToString(value).toRequestBody(null)
                                )
                            }
                        }
                    }.build()
            }
            mediaType == FormUrlEncMediaType -> {
                FormBody.Builder().apply {
                    // content's type *must* be Map<String, Any?>
                    @Suppress("UNCHECKED_CAST")
                    (content as Map<String, Any?>).forEach { (key, value) ->
                        add(key, parameterToString(value))
                    }
                }.build()
            }
            mediaType == JsonMediaType -> Serializer.json.encodeToString(content).toRequestBody(
                mediaType.toMediaTypeOrNull()
            )
            mediaType == XmlMediaType -> throw UnsupportedOperationException("xml not currently supported.")
            // TODO: this should be extended with other serializers
            else -> throw UnsupportedOperationException("requestBody currently only supports JSON body and File body.")
        }

    protected inline fun <reified T: Any?> responseBody(body: ResponseBody?, mediaType: String? = JsonMediaType): T? {
        if(body == null) {
            return null
        }
        val bodyContent = body.string()
        if (bodyContent.isEmpty()) {
            return null
        }
        return when(mediaType) {
            JsonMediaType -> Serializer.json.decodeFromString<T>(bodyContent)
            else ->  throw UnsupportedOperationException("responseBody currently only supports JSON body.")
        }
    }

    protected fun updateAuthParams(requestConfig: RequestConfig) {
        if (requestConfig.query["token"].isNullOrEmpty()) {
            if (apiKey["token"] != null) {
                if (apiKeyPrefix["token"] != null) {
                    requestConfig.query["token"] = listOf(apiKeyPrefix["token"]!! + " " + apiKey["token"]!!)
                } else {
                    requestConfig.query["token"] = listOf(apiKey["token"]!!)
                }
            }
        }
    }

    protected inline fun <reified T: Any?> request(requestConfig: RequestConfig, body : JsonObject? = null): ApiInfrastructureResponse<T?> {
        val httpUrl = baseUrl.toHttpUrlOrNull() ?: throw IllegalStateException("baseUrl is invalid.")

        // take authMethod from operation
        updateAuthParams(requestConfig)

        val url = httpUrl.newBuilder()
            .addPathSegments(requestConfig.path.trimStart('/'))
            .apply {
                requestConfig.query.forEach { query ->
                    query.value.forEach { queryValue ->
                        addQueryParameter(query.key, queryValue)
                    }
                }
            }.build()

        // take content-type/accept from spec or set to default (application/json) if not defined
        if (requestConfig.headers[ContentType].isNullOrEmpty()) {
            requestConfig.headers[ContentType] = JsonMediaType
        }
        if (requestConfig.headers[Accept].isNullOrEmpty()) {
            requestConfig.headers[Accept] = JsonMediaType
        }
        val headers = requestConfig.headers

        if(headers[ContentType] ?: "" == "") {
            throw kotlin.IllegalStateException("Missing Content-Type header. This is required.")
        }

        if(headers[Accept] ?: "" == "") {
            throw kotlin.IllegalStateException("Missing Accept header. This is required.")
        }

        // TODO: support multiple contentType options here.
        val contentType = (headers[ContentType] as String).substringBefore(";").toLowerCase()

        val request = when (requestConfig.method) {
            RequestMethod.DELETE -> Request.Builder().url(url).delete(requestBody(body, contentType))
            RequestMethod.GET -> Request.Builder().url(url)
            RequestMethod.HEAD -> Request.Builder().url(url).head()
            RequestMethod.PATCH -> Request.Builder().url(url).patch(requestBody(body, contentType))
            RequestMethod.PUT -> Request.Builder().url(url).put(requestBody(body, contentType))
            RequestMethod.POST -> Request.Builder().url(url).post(requestBody(body, contentType))
            RequestMethod.OPTIONS -> Request.Builder().url(url).method("OPTIONS", null)
        }.apply {
            headers.forEach { header -> addHeader(header.key, header.value) }
        }.build()

        val response = client.newCall(request).execute()
        val accept = response.header(ContentType)?.substringBefore(";")?.toLowerCase()

        // TODO: handle specific mapping types. e.g. Map<int, Class<?>>
        when {
            response.isRedirect -> return Redirection(
                    response.code,
                    response.headers.toMultimap()
            )
            response.isInformational -> return Informational(
                    response.message,
                    response.code,
                    response.headers.toMultimap()
            )
            response.isSuccessful -> return Success(
                    responseBody(response.body, accept),
                    response.code,
                    response.headers.toMultimap()
            )
            response.isClientError -> return ClientError(
                    response.message,
                    response.body?.string(),
                    response.code,
                    response.headers.toMultimap()
            )
            else -> return ServerError(
                    response.message,
                    response.body?.string(),
                    response.code,
                    response.headers.toMultimap()
            )
        }
    }

    protected fun parameterToString(value: Any?): String {
        return when (value) {
            null -> ""
            is Array<*> -> toMultiValue(value, "csv").toString()
            is Iterable<*> -> toMultiValue(value, "csv").toString()
            is OffsetDateTime, is OffsetTime, is LocalDateTime, is LocalDate, is LocalTime, is Date -> parseDateToQueryString(value)
            else -> value.toString()
        }
    }

    protected inline fun <reified T: Any> parseDateToQueryString(value : T): String {
        /*
        .replace("\"", "") converts the json object string to an actual string for the query parameter.
        The moshi or gson adapter allows a more generic solution instead of trying to use a native
        formatter. It also easily allows to provide a simple way to define a custom date format pattern
        inside a gson/moshi adapter.
        */
        return Serializer.json.encodeToString(value).replace("\"", "")
    }
}
