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
 * @param data Array of economic data for requested code.
 * @param code Finnhub economic code
 */

data class EconomicData (
    /* Array of economic data for requested code. */
    @Json(name = "data")
    val data: kotlin.collections.List<kotlin.Any>? = null,
    /* Finnhub economic code */
    @Json(name = "code")
    val code: kotlin.String? = null
) : Serializable {
	companion object {
		private const val serialVersionUID: Long = 123
	}

}

