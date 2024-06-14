package com.fyp.sctsma.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fyp.sctsma.api.RetrofitInstance
import com.fyp.sctsma.model.userData.LoginUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ScreenModel {
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private val tag = "RegisterViewModel"
    val password = MutableLiveData<String>()
    var phoneNumber = MutableLiveData<String>()
    var errorMessage = MutableLiveData<String>()
    //primitives that will be submitted to the en
    val username = MutableLiveData<String>()
    //checks if the registration is successful before moving to the next screen
    private val _loginSuccess = MutableLiveData(false)
    //we are referring this value on the composable screen
    val loginSuccess: MutableLiveData<Boolean> = _loginSuccess
    val usernameregister = MutableLiveData<String>()
    val accessToken = MutableLiveData<String>("")

    private fun validatePhoneNumber(): Boolean {
        val regex = Regex("^255\\d{0,9}$")
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

        // Add more validation checks as needed
        return true
    }
    fun loginUser() {
        if (!validateInput()) {
            return
        }

        //this part down here will run only when the validateInput() is true so the return block can be ignored
        screenModelScope.launch(Dispatchers.Main) {

            try {
                isLoading.value = true
                val user = LoginUser(
                    password = password.value?:"",
                    username = phoneNumber.value?:"",
                )
                val response = RetrofitInstance.appApi.loginUser(user)
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    // Extract data from registrationResponse
                    isLoading.value = false
                    val status = loginResponse?.status


                    if(status == "1"){
                        _loginSuccess.value = true // Signal success
                        username.value = loginResponse.data.user.username
                        val lockNumber = loginResponse.data.user.lockNumber
                        accessToken.value = loginResponse.data.accessToken
                        Log.i(tag, "Registration successful: $username, $lockNumber,$accessToken")
                    }


                    // ... extract other fields as needed

                } else {
                    // Handle error response
                    val errorBody = response.errorBody()?.string()
                    Log.e(tag, "Login failed: $errorBody")
                    errorMessage.value = "Either the username or password is incorrect. Please try again."
                }

            } catch (e: Exception) {
                isLoading.value = false
                Log.e(tag, "Login failed", e)
                errorMessage.value = "Oops! Something Went wrong, try again later"
                Log.e(tag, e.message.toString())
            }
        }
    }
}

