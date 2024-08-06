package com.fyp.sctsma.model.orders

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderItemResponse (
    @Json(name = "rowId") val rowId: String,
    @Json(name = "productName") val productName: String,
    @Json(name = "description") val description: String,
    @Json(name = "amount") val amount: Double,
    @Json(name = "quantity") val quantity: Int,
    @Json(name = "ownerFullName") val ownerFullName: String?,
    @Json(name = "ownerLockNumber") val ownerLockNumber: String,
    @Json(name = "ownerPhoneNumber") val ownerPhoneNumber: String,
    @Json(name = "pickupCenter") val pickupCenter: String?,
    @Json(name = "origin") val origin: String?,
    @Json(name = "destination") val destination: String?,
    @Json(name = "targetPerson") val targetPerson: String,
    @Json(name = "agreementCreatedAt") val agreementCreatedAt: String,
    @Json(name = "status") val status: String,
    @Json(name = "cancelled") val cancelled: Boolean
)
