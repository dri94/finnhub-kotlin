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
 * @param s Symbol.
 * @param skip Number of ticks skipped.
 * @param count Number of ticks returned. If <code>count</code> < <code>limit</code>, all data for that date has been returned.
 * @param total Total number of ticks for that date.
 * @param v List of volume data.
 * @param p List of price data.
 * @param t List of timestamp in UNIX ms.
 * @param x List of venues/exchanges.
 */

data class TickData (
    /* Symbol. */
    @Json(name = "s")
    val s: kotlin.String? = null,
    /* Number of ticks skipped. */
    @Json(name = "skip")
    val skip: kotlin.Long? = null,
    /* Number of ticks returned. If <code>count</code> < <code>limit</code>, all data for that date has been returned. */
    @Json(name = "count")
    val count: kotlin.Long? = null,
    /* Total number of ticks for that date. */
    @Json(name = "total")
    val total: kotlin.Long? = null,
    /* List of volume data. */
    @Json(name = "v")
    val v: kotlin.collections.List<kotlin.Float>? = null,
    /* List of price data. */
    @Json(name = "p")
    val p: kotlin.collections.List<kotlin.Float>? = null,
    /* List of timestamp in UNIX ms. */
    @Json(name = "t")
    val t: kotlin.collections.List<kotlin.Long>? = null,
    /* List of venues/exchanges. */
    @Json(name = "x")
    val x: kotlin.collections.List<kotlin.String>? = null
) : Serializable {
	companion object {
		private const val serialVersionUID: Long = 123
	}

}

