package com.fyp.sctsma.model.userData

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    val data: Data?,
    val message: String?,
    val status: String?
)









