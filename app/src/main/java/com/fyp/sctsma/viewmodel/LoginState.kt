package com.fyp.sctsma.viewmodel

sealed class LoginState {
    data object Success : LoginState()
    data class Error(val message: String) : LoginState()
}