package com.fyp.sctsma.model.userData

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Data(
    val user: User?,
    val accessToken: String?,
    val expirationTime: String?
)