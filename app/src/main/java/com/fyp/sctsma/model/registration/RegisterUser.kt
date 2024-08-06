package com.fyp.sctsma.model.registration

//user registration model
data class RegisterUser(
    val password: String,
    val contactPhoneNumber: String,
    val accountType: String
)