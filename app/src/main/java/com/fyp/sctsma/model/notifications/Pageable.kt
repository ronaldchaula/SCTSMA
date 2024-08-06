package com.fyp.sctsma.model.notifications

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pageable(
    val pageNumber: Int,
    val pageSize: Int,
    val sort: Sort,
    val offset: Long,
    val unpaged: Boolean,
    val paged: Boolean
)
