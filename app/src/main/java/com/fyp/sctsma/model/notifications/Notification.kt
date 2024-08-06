package com.fyp.sctsma.model.notifications

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Notification(
    val data: NotificationData,
    val message: String,
    val status: String
)








