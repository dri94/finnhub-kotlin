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
 * @param symbol Symbol.
 * @param date Ex-Dividend date.
 * @param amount Amount in local currency.
 * @param adjustedAmount Adjusted dividend.
 * @param payDate Pay date.
 * @param recordDate Record date.
 * @param declarationDate Declaration date.
 * @param currency Currency.
 */

@Serializable
data class Dividends (
    /* Symbol. */
    @SerialName("symbol")
    val symbol: String? = null,
    /* Ex-Dividend date. */
    @SerialName("date")
    val date: String? = null,
    /* Amount in local currency. */
    @SerialName("amount")
    val amount: Float? = null,
    /* Adjusted dividend. */
    @SerialName("adjustedAmount")
    val adjustedAmount: Float? = null,
    /* Pay date. */
    @SerialName("payDate")
    val payDate: String? = null,
    /* Record date. */
    @SerialName("recordDate")
    val recordDate: String? = null,
    /* Declaration date. */
    @SerialName("declarationDate")
    val declarationDate: String? = null,
    /* Currency. */
    @SerialName("currency")
    val currency: String? = null
) {
	companion object {
    }

}

