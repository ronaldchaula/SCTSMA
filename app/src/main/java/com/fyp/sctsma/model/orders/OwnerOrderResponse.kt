package com.fyp.sctsma.model.orders

import com.fyp.sctsma.model.agreements.AgreementData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OwnerOrderResponse(
    val data: AgreementData?,
    val message: String?,
    val status: String?
)
