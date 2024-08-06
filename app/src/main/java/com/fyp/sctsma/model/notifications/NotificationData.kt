package com.fyp.sctsma.model.notifications

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NotificationData(
    val content: List<NotificationContent>,
    val pageable: Pageable,
    val totalPages: Int,
    val totalElements: Int,
    val last: Boolean,
    val size: Int,
    val number: Int,
    val sort: Sort,
    val numberOfElements: Int,
    val first: Boolean,
    val empty: Boolean
)
