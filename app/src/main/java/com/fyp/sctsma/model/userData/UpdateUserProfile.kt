package com.fyp.sctsma.model.userData

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateUserProfile(
    @Json(name = "email") val email: String,
    @Json(name = "contactName") val contactName: String,
    @Json(name = "contactEmail") val contactEmail: String,
    @Json(name = "firstName") val firstName: String,
    @Json(name = "lastName") val lastName: String,
   // @Json(name = "dateOfBirth") val dateOfBirth: String,
    @Json(name = "gender") val gender: String,
    @Json(name = "nationality") val nationality: String,
    @Json(name = "addressLine1") val addressLine1: String,
    @Json(name = "city") val city: String,
    @Json(name = "state") val state: String,
    @Json(name = "postalCode") val postalCode: String,
    @Json(name = "country") val country: String,
    @Json(name = "latitude") val latitude: String?,
    @Json(name = "longitude") val longitude: String?
)
