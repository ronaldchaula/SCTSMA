package com.fyp.sctsma.model.notifications

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NotificationContent(
    val rowId: String,
    val message: String,
    val user: String,
    val status: Int
)
