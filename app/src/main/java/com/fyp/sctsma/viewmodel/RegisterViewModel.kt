package com.fyp.sctsma.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fyp.sctsma.api.RetrofitInstance
import com.fyp.sctsma.model.registration.RegisterUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterViewModel: ScreenModel {


    val isLoading = MutableLiveData(false)
    private val tag = "RegisterViewModel"
    val password = MutableLiveData<String>()
    val confirmPassword= MutableLiveData<String>()
    var phoneNumber = MutableLiveData<String>()
    val accountType = MutableLiveData("Business")
    var errorMessage = MutableLiveData<String?>()
   //primitives that will be submitted to the end point
    //checks if the registration is successful before moving to the next screen
    private val _registrationSuccess = MutableLiveData(false)
    //we are referring this value on the composable screen
    val registrationSuccess: MutableLiveData<Boolean> = _registrationSuccess

    private val _otp = MutableLiveData("")
    val obtainedOTP: MutableLiveData<String> = _otp
    //assign values from the live data to the registerUser object

    private fun validatePhoneNumber(): Boolean {
        val regex = Regex("^255\\d*$")
        return phoneNumber.value?.matches(regex) ?: false
    }

    private fun validatePassword(): Boolean {
        val passwordPattern = "^.{6,12}\$".toRegex()
        return password.value?.matches(passwordPattern) ?: false
    }
    private fun validateConfirmPassword(): Boolean {
        val passwordPattern = "^.{6,12}\$".toRegex()
        return confirmPassword.value?.matches(passwordPattern) ?: false
    }
    private fun validateInput(): Boolean {
        if (phoneNumber.value.isNullOrEmpty()) {
            errorMessage.value = "Phone Number cannot be empty"
            return false
        }
        if (!validatePhoneNumber()) {
            errorMessage.value = "Phone number begins with 255 and not less than 12 characters"
            return false
        }
        if (password.value.isNullOrEmpty()) {
            errorMessage.value = "Password cannot be empty"
            return false
        }
        if (!validatePassword()) {
            errorMessage.value = "Password can't be less than 6 characters and greater than 12 characters"
            return false
        }
        if (confirmPassword.value.isNullOrEmpty()) {
            errorMessage.value = "Confirm Password cannot be empty"
            return false
        }
        if (!validateConfirmPassword()) {
            errorMessage.value = "Confirm Password can't be less than 6 characters and greater than 12 characters"
            return false
        }
        if (confirmPassword.value != password.value) {
            errorMessage.value = "Passwords don't match"
            return false
        }
        // Add more validation checks as needed
        return true
    }
    fun registerUser() {
        if (!validateInput()) {
            return
        }
errorMessage.value = null
        //this part down here will run only when the validateInput() is true so the return block can be ignored
        screenModelScope.launch(Dispatchers.Main) {

            try {
                val user = RegisterUser(
                    password = password.value ?: "",
                    accountType = accountType.value ?: "",
                    contactPhoneNumber = phoneNumber.value ?: ""
                )
                isLoading.value = true
                val response = RetrofitInstance.unAuthenticatedApi.sendRegistrationData(user)
                isLoading.value = false
                if (response.isSuccessful) {
                    val registrationResponse = response.body()
                    // Extract data from registrationResponse
                    val message = registrationResponse?.message
                    val status = registrationResponse?.status
                    _otp.value = registrationResponse?.data?.otp ?: "" // Store OTP
                    if(message == "success"){
                        _registrationSuccess.value = true // Signal success
                    }

                    val dataMessage = registrationResponse?.data?.message
                    // ... extract other fields as needed
                    Log.i(tag, "Registration successful: $message, $status,$obtainedOTP,$dataMessage")
                } else {
                    // Handle error response
                    val errorBody = response.errorBody()?.string()
                    Log.e(tag, "Registration failed: $errorBody")
                    errorMessage.value = "Registration failed. Please try again."
                }

            } catch (e: Exception) {
                isLoading.value = false
                Log.e(tag, "Registration failed", e)
                errorMessage.value = "Oops! Something Went wrong"
                Log.e(tag, e.message.toString())
            }
        }
    }
}




