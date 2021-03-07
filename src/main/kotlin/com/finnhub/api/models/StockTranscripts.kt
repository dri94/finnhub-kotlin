/**
* Finnhub API
* No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
*
* The version of the OpenAPI document: 1.0.0
* 
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package com.finnhub.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 
 * @param id Transcript's ID used to get the <a href=\"#transcripts\">full transcript</a>.
 * @param title Title.
 * @param time Time of the event.
 * @param year Year of earnings result in the case of earnings call transcript.
 * @param quarter Quarter of earnings result in the case of earnings call transcript.
 */

@Serializable
data class StockTranscripts (
    /* Transcript's ID used to get the <a href=\"#transcripts\">full transcript</a>. */
    @SerialName("id")
    val id: kotlin.String? = null,
    /* Title. */
    @SerialName("title")
    val title: kotlin.String? = null,
    /* Time of the event. */
    @SerialName("time")
    val time: kotlin.String? = null,
    /* Year of earnings result in the case of earnings call transcript. */
    @SerialName("year")
    val year: kotlin.Long? = null,
    /* Quarter of earnings result in the case of earnings call transcript. */
    @SerialName("quarter")
    val quarter: kotlin.Long? = null
)

