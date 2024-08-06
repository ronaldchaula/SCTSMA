package com.fyp.sctsma.model.userData

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    val rowId: String,
    var username: String,
    var email: String?,
    val contactName: String?,
    var contactEmail: String?,
    val contactPhoneNumber: String,
    val accountType: String,
    val lockNumber: String?,
    var firstName: String?,
    var lastName: String?,
    var dateOfBirth: String?,
    var gender: String?,
    var nationality: String?,
    var addressLine1: String?,
    var city: String?,
    var state: String?,
    var postalCode: String?,
    var country: String?,
    val latitude: String?,
    val longitude: String?,
    val photoUrl: String?,
    val roles: List<Role>
)