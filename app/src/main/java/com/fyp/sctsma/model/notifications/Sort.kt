package com.fyp.sctsma.model.notifications

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sort(
    val sorted: Boolean,
    val empty: Boolean,
    val unsorted: Boolean
)
