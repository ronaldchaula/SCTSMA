package com.fyp.sctsma.model.registration

import com.fyp.sctsma.model.Data


data class RegistrationResponse(
    val data: Data,
    val message:String,
    val status:String
)