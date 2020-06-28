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
 * @param country Country of company's headquarter.
 * @param currency Currency used in company filings.
 * @param exchange Listed exchange.
 * @param name Company name.
 * @param ticker Company symbol/ticker as used on the listed exchange.
 * @param ipo IPO date.
 * @param marketCapitalization Market Capitalization.
 * @param shareOutstanding Number of oustanding shares.
 * @param logo Logo image.
 * @param phone Company phone number.
 * @param weburl Company website.
 * @param finnhubIndustry Finnhub industry classification.
 */

data class CompanyProfile2 (
    /* Country of company's headquarter. */
    @Json(name = "country")
    val country: kotlin.String? = null,
    /* Currency used in company filings. */
    @Json(name = "currency")
    val currency: kotlin.String? = null,
    /* Listed exchange. */
    @Json(name = "exchange")
    val exchange: kotlin.String? = null,
    /* Company name. */
    @Json(name = "name")
    val name: kotlin.String? = null,
    /* Company symbol/ticker as used on the listed exchange. */
    @Json(name = "ticker")
    val ticker: kotlin.String? = null,
    /* IPO date. */
    @Json(name = "ipo")
    val ipo: java.time.LocalDate? = null,
    /* Market Capitalization. */
    @Json(name = "marketCapitalization")
    val marketCapitalization: kotlin.Float? = null,
    /* Number of oustanding shares. */
    @Json(name = "shareOutstanding")
    val shareOutstanding: kotlin.Float? = null,
    /* Logo image. */
    @Json(name = "logo")
    val logo: kotlin.String? = null,
    /* Company phone number. */
    @Json(name = "phone")
    val phone: kotlin.String? = null,
    /* Company website. */
    @Json(name = "weburl")
    val weburl: kotlin.String? = null,
    /* Finnhub industry classification. */
    @Json(name = "finnhubIndustry")
    val finnhubIndustry: kotlin.String? = null
) : Serializable {
	companion object {
		private const val serialVersionUID: Long = 123
	}

}

