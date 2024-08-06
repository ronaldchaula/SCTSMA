package com.fyp.sctsma.repository

import android.content.Context
import android.provider.Settings.Global.putString
import com.fyp.sctsma.model.userData.Data
import com.fyp.sctsma.model.userData.User
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AppPrefRepository(
    context : Context
) {
    private val sharedPreferences = context.getSharedPreferences("SCTSMA_prefs", Context.MODE_PRIVATE)
 //this function saves the user data when the user logs in
 // Function to save user data when the user logs in
 fun saveUserData(user: User) {
     sharedPreferences.edit().apply {
         putString("rowId", user.rowId)
         putString("username", user.username)
         putString("email", user.email)
         putString("contactName", user.contactName)
         putString("contactEmail", user.contactEmail)
         putString("contactPhoneNumber", user.contactPhoneNumber)
         putString("accountType", user.accountType)
         putString("lockNumber", user.lockNumber)
         putString("firstName", user.firstName)
         putString("lastName", user.lastName)
         putString("dateOfBirth", user.dateOfBirth)
         putString("gender", user.gender)
         putString("nationality", user.nationality)
         putString("addressLine1", user.addressLine1)
         putString("city", user.city)
         putString("state", user.state)
         putString("postalCode", user.postalCode)
         putString("country", user.country)
         putString("latitude", user.latitude)
         putString("longitude", user.longitude)
         putString("photoUrl", user.photoUrl)
         apply()
     }
 }
    //this function saves the access data when the user logs in
    fun saveAccessData(access: Data){
        sharedPreferences.edit().apply{
            putString("accessToken", access.accessToken)
            putString("expirationTime", access.expirationTime)
                .apply()
        }
    }

    //this function returns the user data when the user logs in
    fun getUserData(): User? {
        val rowId = sharedPreferences.getString("rowId", null)
        val username = sharedPreferences.getString("username", null)
        val email = sharedPreferences.getString("email", null)
        val contactName = sharedPreferences.getString("contactName", null)
        val contactEmail = sharedPreferences.getString("contactEmail", null)
        val contactPhoneNumber = sharedPreferences.getString("contactPhoneNumber", null)
        val accountType = sharedPreferences.getString("accountType", null)
        val lockNumber = sharedPreferences.getString("lockNumber", null)
        val firstName = sharedPreferences.getString("firstName", null)
        val lastName = sharedPreferences.getString("lastName", null)
        val dateOfBirth = sharedPreferences.getString("dateOfBirth", null)
        val gender = sharedPreferences.getString("gender", null)
        val nationality = sharedPreferences.getString("nationality", null)
        val addressLine1 = sharedPreferences.getString("addressLine1", null)
        val city = sharedPreferences.getString("city", null)
        val state = sharedPreferences.getString("state", null)
        val postalCode = sharedPreferences.getString("postalCode", null)
        val country = sharedPreferences.getString("country", null)
        val latitude = sharedPreferences.getString("latitude", null)
        val longitude = sharedPreferences.getString("longitude", null)
        val photoUrl = sharedPreferences.getString("photoUrl", null)

        return if (rowId != null && username != null && contactPhoneNumber != null && accountType != null) {
            User(
                rowId,
                username,
                email,
                contactName,
                contactEmail,
                contactPhoneNumber,
                accountType,
                lockNumber,
                firstName,
                lastName,
                dateOfBirth,
                gender,
                nationality,
                addressLine1,
                city,
                state,
                postalCode,
                country,
                latitude,
                longitude,
                photoUrl,
                emptyList() // Initialize roles as an empty list
            )
        } else {
            null
        }
    }
    fun getAccessData(): Data? {
        val accessToken = sharedPreferences.getString("accessToken", null)
        val expirationTime = sharedPreferences.getString("expirationTime", null)

        if (accessToken != null && expirationTime != null) {
            // Parse the expirationTime as a LocalDateTime
            val expirationDateTime = LocalDateTime.parse(expirationTime, DateTimeFormatter.ISO_DATE_TIME)

            // Get the current time
            val currentDateTime = LocalDateTime.now()

            // Check if the current time is before the expiration time
            if (currentDateTime.isBefore(expirationDateTime)) {
                // The login is still valid
                return Data(user = null, accessToken, expirationTime) // Pass null for claim
            }
        }

        // The login is not valid
        return null
    }
    // check user registration completeneness



    //this function is used in a logout function to delete the user data
    fun clearUserData() {
        sharedPreferences.edit().clear().apply()
    }
    }
