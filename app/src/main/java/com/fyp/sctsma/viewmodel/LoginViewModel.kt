package com.fyp.sctsma.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val phoneNumber = MutableLiveData<String>()
    val password = MutableLiveData<String>()



}



