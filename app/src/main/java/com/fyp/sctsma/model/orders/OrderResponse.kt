package com.fyp.sctsma.model.orders

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderResponse(
    @Json(name = "data") val data: OrderItemResponse,
    @Json(name = "message") val message: String,
    @Json(name = "status") val status: String
)
