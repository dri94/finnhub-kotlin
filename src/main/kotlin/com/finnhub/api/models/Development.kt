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


import com.squareup.moshi.Json
import java.io.Serializable
/**
 * 
 * @param symbol Company symbol.
 * @param datetime Published time in <code>YYYY-MM-DD HH:MM:SS</code> format.
 * @param headline Development headline.
 * @param description Development description.
 */

data class Development (
    /* Company symbol. */
    @Json(name = "symbol")
    val symbol: kotlin.String? = null,
    /* Published time in <code>YYYY-MM-DD HH:MM:SS</code> format. */
    @Json(name = "datetime")
    val datetime: kotlin.String? = null,
    /* Development headline. */
    @Json(name = "headline")
    val headline: kotlin.String? = null,
    /* Development description. */
    @Json(name = "description")
    val description: kotlin.String? = null
) : Serializable {
	companion object {
		private const val serialVersionUID: Long = 123
	}

}

