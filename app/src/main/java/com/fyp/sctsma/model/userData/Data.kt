package com.fyp.sctsma.model.userData

data class Data(
    val user: User,
    val accessToken: String,
    val expirationTime: String,
    val claims: Claims
)