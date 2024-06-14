package com.fyp.sctsma.model

import com.fyp.sctsma.model.Data


data class RegistrationResponse(
    val data: Data,
    val message:String,
    val status:String
)