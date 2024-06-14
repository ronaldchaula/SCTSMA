package com.fyp.sctsma.model.userData

data class Claims(
    val sub: String,
    val iat: Int,
    val exp: Int
)