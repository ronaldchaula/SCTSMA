package com.fyp.sctsma.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fyp.sctsma.api.RetrofitInstance
import com.fyp.sctsma.model.otp.OTP
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OtpViewModel: ScreenModel {


    //opt String is collected from the UI and is checked through out the view Model
    val otp = MutableLiveData<String>()
    private val tag = "OtpViewModel"
    val errorMessage = MutableLiveData<String?>()
    val isLoading = MutableLiveData<Boolean>(false)// State for loading indicator
    val activationSuccess = MutableLiveData<Boolean>(false) // State for activation success
    var otpMessage = MutableLiveData<String>()
    val otpStatus = MutableLiveData<String>()
    val otpData = MutableLiveData<String>()



    private fun validateInput(): Boolean {
        if (otp.value.isNullOrEmpty()) {
            errorMessage.value = "OTP can not be empty"
            return false
        }
        if (otp.value!!.length >6) {
            errorMessage.value = "OTP cannot be greater than 6 numbers"
            return false
        }
        if (otp.value!!.length < 6) {
            errorMessage.value = "OTP cannot be less than 6 numbers"
            return false
        }
        // Add more validation checks as needed
        return true
    }



        fun activateAccount() {
                if (!validateInput()) {
                    return
                }
            errorMessage.value = null // Clear any previous error message
        screenModelScope.launch(Dispatchers.Main)  {
                    try {
                        isLoading.value = true // Show loading indicator
                        val otpObject = OTP(otp.value?:"")
                        Log.i(tag, "Sending OTP: ${otp.value}")
                        val otpResponse = RetrofitInstance.unAuthenticatedApi.activateAccount(otpObject)
                        isLoading.value = false // Hide loading indicator
                        if (otpResponse.isSuccessful) {
                            // if successful, set activationSuccess to true
                            val messageBody= otpResponse.body()?.message.toString()
                            otpMessage.value = messageBody
                            otpStatus.value = otpResponse.body()?.status.toString()
                            otpData.value = otpResponse.body()?.data.toString()
                            activationSuccess.value = true // Signal activation success
                            Log.i(tag, "Activation successful")
                        } else {
                            // Handle error response
                            val errorBody = otpResponse.errorBody()?.string()
                            errorMessage.value = "wrong OTP"
                            Log.e(tag, "Activation failed: $errorBody")
                            errorMessage.value = "Wrong OTP. Please try again."
                        }

                    } catch (e: Exception) {
                        isLoading.value = false // Hide loading indicator
                        Log.e(tag, "Activation failed", e)
                        errorMessage.value = "Oops! Something went wrong."
                    }
                }
            }
}



