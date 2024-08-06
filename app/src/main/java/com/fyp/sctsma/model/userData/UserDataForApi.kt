package com.fyp.sctsma.model.userData

data class UserDataForApi(
    val email: String?,
    val contactName: String?,
    val contactEmail: String?,
    val firstName: String?,
    val lastName: String?,
    val dateOfBirth: String?,
    val gender: String?,
    val nationality: String?,
    val addressLine1: String?,
    val city: String?,
    val state: String?,
    val postalCode: String?,
    val country: String?,
    val latitude: String?,
    val longitude: String?,
    val photoUrl: String?
)
