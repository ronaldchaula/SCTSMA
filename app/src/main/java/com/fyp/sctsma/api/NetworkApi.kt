package com.fyp.sctsma.api

import com.fyp.sctsma.model.userData.LoginUser
import com.fyp.sctsma.model.OTP
import com.fyp.sctsma.model.OTPResponse
import com.fyp.sctsma.model.RegisterUser
import com.fyp.sctsma.model.RegistrationResponse
import com.fyp.sctsma.model.userData.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NetworkApi {

    //register a user
    @POST("/pesalock/v1/auth/register")
    suspend fun sendRegistrationData(@Body user: RegisterUser): Response<RegistrationResponse>
    //sending OTP to the server to activate the account
    @POST("/pesalock/v1/auth/activate-account")
    suspend fun activateAccount(@Body otpItem: OTP): Response<OTPResponse>
    //login a user
    @POST("/pesalock/v1/auth/login")
    suspend fun loginUser(@Body loginUser: LoginUser): Response<LoginResponse>

}