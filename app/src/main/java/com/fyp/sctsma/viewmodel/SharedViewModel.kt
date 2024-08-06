package com.fyp.sctsma.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.fyp.sctsma.api.RetrofitInstance
import com.fyp.sctsma.model.agreements.Agreement
import com.fyp.sctsma.model.notifications.NotificationContent
import com.fyp.sctsma.model.orders.OrderItem
import com.fyp.sctsma.model.orders.OrderPayment
import com.fyp.sctsma.model.userData.UpdateUserProfile
import com.fyp.sctsma.repository.AppPrefRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(
    context: Context
):ScreenModel{

    private val tag = "SharedViewModel"
    val appPrefRepository = AppPrefRepository(context)
    val username = MutableLiveData<String?>(appPrefRepository.getUserData()?.firstName)
    private val errorMessage = MutableLiveData("")
    val error = errorMessage
    private val isLoading = MutableLiveData(false)
    val loading:MutableLiveData<Boolean> get() = isLoading

    var orderTitle = MutableLiveData<String>()
    val orderQty = MutableLiveData<String>()
    val orderDescription = MutableLiveData<String>()
    val orderCost = MutableLiveData<String>()
    val lipaNumber = MutableLiveData<String?>(appPrefRepository.getUserData()?.lockNumber)
    val success = MutableLiveData(false)

    //observer logged in status
    private val _isLoggedIn = MutableLiveData(false)
    val isLoggedIn: MutableLiveData<Boolean> = _isLoggedIn

    //getting targeted to you agreements
    private val _agreements = MutableLiveData<List<Agreement>?>(emptyList())
    val agreements: MutableLiveData<List<Agreement>?> = _agreements
    //getting your created agreements
    private val _myAgreements = MutableLiveData<List<Agreement>?>(emptyList())
    val myAgreements: MutableLiveData<List<Agreement>?> = _myAgreements

    //getting agreement details
    private val _agreementDetails = MutableLiveData<Agreement?>()
    val agreementDetails: MutableLiveData<Agreement?> get() = _agreementDetails

    private val _notification = MutableLiveData<List<NotificationContent>?>(emptyList())
    val notification: MutableLiveData<List<NotificationContent>?> get() = _notification
    init {
        _isLoggedIn.value = appPrefRepository.getAccessData()?.accessToken != null
    }

    // ... other methods

    fun logout() {
        appPrefRepository.clearUserData()
        username.value = null
        _isLoggedIn.value = false
    }


    fun fetchAgreements() {
        screenModelScope.launch {
            isLoading.value = true
            try {
                val response =  RetrofitInstance.authenticatedApiCall(appPrefRepository).getAgreements()
                _agreements.value = response.data?.content
                errorMessage.value = null // Clear any previous errors
            } catch (e: Exception) {
                _agreements.value = emptyList()
                errorMessage.value = "Failed to fetch agreements: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }
    fun fetchOwnerCreatedAgreements() {
        screenModelScope.launch {
            isLoading.value = true
            try {
                val response =  RetrofitInstance.authenticatedApiCall(appPrefRepository).getOwnerCreatedAgreements()
                isLoading.value = false
                _myAgreements.value = response.data?.content
                errorMessage.value = null // Clear any previous errors
            } catch (e: Exception) {
                _myAgreements.value = emptyList()
                errorMessage.value = "Failed to fetch your orders: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }

    fun fetchAgreementDetails(
        agreementId: String
    ) {
        screenModelScope.launch {
            isLoading.value = true
            try {
                val response =  RetrofitInstance.authenticatedApiCall(appPrefRepository).getDetailedAgreements(agreementId)
                isLoading.value = false
                _agreementDetails.value = response.data
                Log.d("DetailsScreenFunction", "Agreement Details: $_agreementDetails")
                errorMessage.value = null // Clear any previous errors
            } catch (e: Exception) {
                _agreementDetails.value = null
                errorMessage.value = "Failed to fetch Details: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }
    fun acceptAgreement(agreementId: String) {
        screenModelScope.launch {
            isLoading.value = true
            try {
                isLoading.value = true
                val response =  RetrofitInstance.authenticatedApiCall(appPrefRepository).acceptAgreement(agreementId)
                Log.d("AcceptAgreement", "Response: ${response.message}")
                isLoading.value = false
                errorMessage.value = null // Clear any previous errors
            } catch (e: Exception) {
                errorMessage.value = "Failed accept order: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }

    fun declineAgreement(agreementId: String) {
        screenModelScope.launch {
            isLoading.value = true
            try {
                val response =  RetrofitInstance.authenticatedApiCall(appPrefRepository).declineAgreement(agreementId)
                isLoading.value = false
                errorMessage.value = null // Clear any previous errors
            } catch (e: Exception) {
                errorMessage.value = "Failed accept order: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }




    private fun validateInput(): Boolean {
        if (orderTitle.value.isNullOrEmpty()) {
            errorMessage.value = "Order title cannot be empty"
            return false
        }
        if (orderQty.value.isNullOrEmpty()) {
            errorMessage.value = "Order quantity cannot be empty"
            return false
        }
        if (orderDescription.value.isNullOrEmpty()) {
            errorMessage.value = "Order description cannot be empty"
            return false
        }
        if (orderCost.value.isNullOrEmpty()) {
            errorMessage.value = "Order cost cannot be empty"
            return false
        }
        if (lipaNumber.value.isNullOrEmpty()) {
            errorMessage.value = "Lipa number cannot be empty"
            return false
        }

        // Add more validation checks as needed
        return true
    }



    fun postOrder() {
        if (!validateInput()) {
            return
        }
        errorMessage.value = null // Clear any previous error message
        screenModelScope.launch(Dispatchers.Main)  {
            try {
                isLoading.value = true
                val orderItem = OrderItem(
                    productName = orderTitle.value ?: "",
                    quantity = orderQty.value?.toInt() ?: 0,
                    description = orderDescription.value ?: "",
                    amount = orderCost.value?.toInt() ?: 0,
                    targetPerson = lipaNumber.value ?: ""
                )
                val response = RetrofitInstance.authenticatedApiCall(appPrefRepository).postAgreement(orderItem)
                isLoading.value = false // Hide loading indicator
                success.value = true
                if (response.isSuccessful) {

                    val agreement = response.body()
                    Log.d(tag, "Agreement: $agreement")
                    Log.d(tag, "Agreement posted successfully")
                }
                // Handle the response
            } catch (e: Exception) {
                isLoading.value = false // Hide loading indicator
                success.value = false

                errorMessage.value = "${e.message}"
            }
        }
    }
    fun orderPayment(
        agreementId: String,
        phoneNumber: String
    ) {
        if (!validateInput()) {
            return
        }
        errorMessage.value = null // Clear any previous error message
        screenModelScope.launch(Dispatchers.Main)  {
            try {
                isLoading.value = true
                val paymentItem = OrderPayment(
                    orderId = agreementId,
                    phoneNumber = phoneNumber
                )
                val response = RetrofitInstance.authenticatedApiCall(appPrefRepository).orderPayment(paymentItem)
                isLoading.value = false // Hide loading indicator
                success.value = true
                if (response.isSuccessful) {

                    val agreement = response.body()
                    Log.d(tag, "Agreement: $paymentItem")
                    Log.d(tag, "Paid Successfully")
                }
                // Handle the response
            } catch (e: Exception) {
                isLoading.value = false // Hide loading indicator
                success.value = false

                errorMessage.value = "${e.message}"
            }
        }
    }

    fun orderDeclinePayment(
        agreementId: String,
        phoneNumber: String
    ) {
        if (!validateInput()) {
            return
        }
        errorMessage.value = null // Clear any previous error message
        screenModelScope.launch(Dispatchers.Main)  {
            try {
                isLoading.value = true
                val paymentItem = OrderPayment(
                    orderId = agreementId,
                    phoneNumber = phoneNumber
                )
                val response = RetrofitInstance.authenticatedApiCall(appPrefRepository).orderDeclinePayment(paymentItem)
                isLoading.value = false // Hide loading indicator
                success.value = true
                if (response.isSuccessful) {

                    val agreement = response.body()
                    Log.d(tag, "Agreement: $paymentItem")
                    Log.d(tag, "Paid Successfully")
                }
                // Handle the response
            } catch (e: Exception) {
                isLoading.value = false // Hide loading indicator
                success.value = false

                errorMessage.value = "${e.message}"
            }
        }
    }
    //send changes from profile to the server
    fun updateUserData() {
        errorMessage.value = null // Clear any previous error message
        screenModelScope.launch(Dispatchers.Main)  {
            try {
                isLoading.value = true
               val userDataForApi = getUserDataForApi()
                val response = RetrofitInstance.authenticatedApiCall(appPrefRepository).updateUserData(getUserDataForApi())
                Log.d(tag, "Response: $response")
                isLoading.value = false // Hide loading indicator
                // Handle the response
            } catch (e: Exception) {
                isLoading.value = false // Hide loading indicator
                errorMessage.value = "${e.message}"
            }
        }
    }
    private fun getUserDataForApi(): UpdateUserProfile{
        val user = appPrefRepository.getUserData() // Assuming getUserData retrieves User object

        val userDataForApi = UpdateUserProfile(
            email = user?.email?:"",
            firstName = user?.firstName?:"",
            lastName = user?.lastName?:"",
            contactName = user?.contactName?:"",
            contactEmail = user?.contactEmail?:"",
            //dateOfBirth = user?.dateOfBirth?:"",
            gender = user?.gender?:"",
            nationality = user?.nationality?:"",
            addressLine1 = user?.addressLine1?:"",
            city = user?.city?:"",
            state = user?.state?:"",
            country = user?.country?:"",
            postalCode = user?.postalCode?:"",
            latitude = (user?.latitude?:0.0).toString(),
            longitude = (user?.longitude?:0.0).toString(),
        )
        return userDataForApi
    }

    fun fetchNotifications() {
        screenModelScope.launch {
            isLoading.value = true
            try {
                val response =  RetrofitInstance.authenticatedApiCall(appPrefRepository).fetchNotifications()
                isLoading.value = false
                _notification.value = response.data.content
                Log.d("NotificationScreenFunction", "Agreement Details: $_notification")
                errorMessage.value = null // Clear any previous errors
            } catch (e: Exception) {
                _notification.value = null
                errorMessage.value = "Failed to fetch Details: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }

}
