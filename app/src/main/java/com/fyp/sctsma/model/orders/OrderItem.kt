package com.fyp.sctsma.model.orders

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderItem(
    @Json(name = "productName") val productName: String,
    @Json(name = "description") val description: String,
    @Json(name = "quantity") val quantity: Int,
    @Json(name = "amount") val amount: Int,
    @Json(name = "targetPerson") val targetPerson: String,
)