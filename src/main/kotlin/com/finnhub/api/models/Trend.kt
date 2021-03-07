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
 * @param adx ADX reading
 * @param trending Whether market is trending or going sideway
 */

@Serializable
data class Trend (
    /* ADX reading */
    @SerialName("adx")
    val adx: Float? = null,
    /* Whether market is trending or going sideway */
    @SerialName("trending")
    val trending: Boolean? = null
) {
	companion object {
    }

}

