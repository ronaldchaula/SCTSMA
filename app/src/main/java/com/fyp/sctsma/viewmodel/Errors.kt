package com.fyp.sctsma.viewmodel

import androidx.lifecycle.MutableLiveData
import cafe.adriel.voyager.core.model.ScreenModel

class Errors():ScreenModel{
     val firstname = MutableLiveData<String>()
     val lastname = MutableLiveData<String>()
     val email = MutableLiveData<String>()
     val password = MutableLiveData<String>()
     val confirmPassword = MutableLiveData<String>()
     val contactPhoneNumber = MutableLiveData<String>()
     val accountType = MutableLiveData<String>()

}