package com.fyp.sctsma.model.userData

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Authority(
    val authority: String?
)