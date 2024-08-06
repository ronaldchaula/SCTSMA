package com.fyp.sctsma.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fyp.sctsma.api.RetrofitInstance
import com.fyp.sctsma.model.userData.LoginUser
import com.fyp.sctsma.repository.AppPrefRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val context: Context) : ScreenModel {
    private val appPrefRepository = AppPrefRepository(context)
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private val TAG = "LoginViewModel"
    val password = MutableLiveData<String>()
    var phoneNumber = MutableLiveData<String>()
    var errorMessage = MutableLiveData<String?>()

    //primitives that will be submitted to the persistent store
    private val _username = MutableLiveData<String?>()
    val username = _username
    private val _loginSuccess = MutableLiveData(false)
    val loginSuccess: MutableLiveData<Boolean> = _loginSuccess
    private val _accessToken = MutableLiveData<String?>("")
    val accessToken = _accessToken
    private val _lockNumber = MutableLiveData<String?>("")
    val lockNumber = _lockNumber





    private fun validatePhoneNumber(): Boolean {
        val regex = Regex("^255\\d*$")
        return phoneNumber.value?.matches(regex) ?: false
    }

    private fun validatePassword(): Boolean {
        val passwordPattern = "^.{6,12}\$".toRegex()
        return password.value?.matches(passwordPattern) ?: false
    }

    private fun validateInput(): Boolean {
        if (phoneNumber.value.isNullOrEmpty()) {
            errorMessage.value = "Phone Number cannot be empty"
            return false
        }
        if (!validatePhoneNumber()) {
            errorMessage.value = "Phone number must begin with 255"
            return false
        }
        if (phoneNumber.value!!.length != 12) {
            errorMessage.value = "Phone number must be 12 digits"
            return false
        }
        if (password.value.isNullOrEmpty()) {
            errorMessage.value = "Password cannot be empty"
            return false
        }
        if (!validatePassword()) {
            errorMessage.value = "Password must have 6-12 characters"
            return false
        }

        // Add more validation checks as needed
        return true
    }
    fun loginUser() {
        if (!validateInput()) {
            return
        }
        errorMessage.value = null
        //this part down here will run only when the validateInput() is true so the return block can be ignored
        screenModelScope.launch(Dispatchers.Main) {

            try {
                isLoading.value = true
                val user = LoginUser(
                    password = password.value?:"",
                    username = phoneNumber.value?:"",
                )
                val response = RetrofitInstance.unAuthenticatedApi.loginUser(user)
                isLoading.value = false
                if (response.isSuccessful) {
                   val responseBody = response.body()
                    Log.i(TAG, "Json response status value: ${response.body()?.status}")
                    Log.i(TAG, "Json response message value: ${response.body()?.message}")
                    Log.i(TAG, "Json response data value: ${response.body()?.data}")
                    Log.i(TAG, "Retrofit network response code: ${response.code()}")
                    Log.i(TAG, "Retrofit network response message: ${response.message()}")
                    Log.i(TAG, "Retrofit network error response: ${response.errorBody()}")
                    Log.i(TAG, "Retrofit network response headers: ${response.headers()}")
                    Log.i(TAG, "Retrofit network raw response: ${response.raw()}")

                if(responseBody?.status == "OK" && responseBody?.message == "success"){

                    _loginSuccess.value = true // Signal success
                    //saving the data to user preferences
                    responseBody.data?.let { data ->
                        appPrefRepository.saveAccessData(data)
                        data.user?.let { user ->
                            appPrefRepository.saveUserData(user)
                        }
                    }

                    //get values from preferences
                    _accessToken.value = appPrefRepository.getAccessData()?.accessToken
                    _lockNumber.value = appPrefRepository.getUserData()?.lockNumber
                    _username.value = appPrefRepository.getUserData()?.username
                }


                    // ... extract other fields as needed

                } else {
                    // Handle error response
                    val errorBody = response.errorBody()?.string()
                    Log.e(TAG, "Login failed: $errorBody")
                    errorMessage.value = "Either the username or password is incorrect. Please try again."
                }

            } catch (e: Exception) {
                isLoading.value = false
                Log.e(TAG, "Login failed", e)
                errorMessage.value = "Oops! Something Went wrong, try again later"
                Log.e(TAG, e.message.toString())
            }
        }
    }
}

