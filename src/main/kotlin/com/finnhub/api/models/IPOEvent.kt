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
 * @param symbol Symbol.
 * @param date IPO date.
 * @param exchange Exchange.
 * @param name Company's name.
 * @param status IPO status. Can take 1 of the following values: <code>expected</code>,<code>priced</code>,<code>withdrawn</code>,<code>filed</code>
 * @param price Projected price or price range.
 * @param numberOfShares Number of shares offered during the IPO.
 * @param totalSharesValue Total shares value.
 */

data class IPOEvent (
    /* Symbol. */
    @Json(name = "symbol")
    val symbol: kotlin.String? = null,
    /* IPO date. */
    @Json(name = "date")
    val date: kotlin.String? = null,
    /* Exchange. */
    @Json(name = "exchange")
    val exchange: kotlin.String? = null,
    /* Company's name. */
    @Json(name = "name")
    val name: kotlin.String? = null,
    /* IPO status. Can take 1 of the following values: <code>expected</code>,<code>priced</code>,<code>withdrawn</code>,<code>filed</code> */
    @Json(name = "status")
    val status: kotlin.String? = null,
    /* Projected price or price range. */
    @Json(name = "price")
    val price: kotlin.String? = null,
    /* Number of shares offered during the IPO. */
    @Json(name = "numberOfShares")
    val numberOfShares: kotlin.Long? = null,
    /* Total shares value. */
    @Json(name = "totalSharesValue")
    val totalSharesValue: kotlin.Long? = null
) : Serializable {
	companion object {
		private const val serialVersionUID: Long = 123
	}

}

