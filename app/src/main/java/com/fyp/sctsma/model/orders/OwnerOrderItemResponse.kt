package com.fyp.sctsma.model.orders

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OwnerOrderItemResponse (
    @Json(name = "rowId") val rowId: String,
    @Json(name = "productName") val productName: String,
    @Json(name = "description") val description: String,
    @Json(name = "amount") val amount: Double,
    @Json(name = "quantity") val quantity: Int,
    @Json(name = "lockNumber") val lockNumber: String,
    @Json(name = "region") val region: String,
    @Json(name = "district") val district: String,
    @Json(name = "shippingAddress") val shippingAddress: String?,
    @Json(name = "status") val status: Int
)
