package com.fyp.sctsma.api

import com.fyp.sctsma.model.agreements.AgreementResponse
import com.fyp.sctsma.model.agreements.DetailedAgreementResponse
import com.fyp.sctsma.model.agreements.UpdatedAgreementResponse
import com.fyp.sctsma.model.notifications.Notification
import com.fyp.sctsma.model.orders.OrderItem
import com.fyp.sctsma.model.orders.OrderPayment
import com.fyp.sctsma.model.orders.OrderResponse
import com.fyp.sctsma.model.orders.OwnerOrderResponse
import com.fyp.sctsma.model.otp.OTP
import com.fyp.sctsma.model.otp.OTPResponse
import com.fyp.sctsma.model.registration.RegisterUser
import com.fyp.sctsma.model.registration.RegistrationResponse
import com.fyp.sctsma.model.userData.LoginResponse
import com.fyp.sctsma.model.userData.LoginUser
import com.fyp.sctsma.model.userData.UpdateUserProfile
import com.fyp.sctsma.model.userData.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

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
    @POST("/pesalock/v1/agreement")
    suspend fun postAgreement(@Body orderItem: OrderItem): Response<OrderResponse>

    //returns the individual user agreements that were targeted to him/her
    @GET("/pesalock/v1/agreement/target-person")
    suspend fun getAgreements(): AgreementResponse
    @GET("/pesalock/v1/agreement/owner")
    suspend fun getOwnerCreatedAgreements(): OwnerOrderResponse

    @GET("/pesalock/v1/agreement/{id}")
    suspend fun getDetailedAgreements(@Path("id") agreementId: String): DetailedAgreementResponse
    @PATCH("pesalock/v1/agreement/accept/{id}")
    suspend fun acceptAgreement(@Path("id") agreementId: String): UpdatedAgreementResponse

    @PATCH("pesalock/v1/agreement/decline/{id}")
    suspend fun declineAgreement(@Path("id") agreementId: String): UpdatedAgreementResponse

    @PATCH("/pesalock/v1/users/update")
    suspend fun updateUserData(@Body userData: UpdateUserProfile)

    @GET("/pesalock/v1/users/get")
    suspend fun fetchRemoteUserData():Response<User>

    @POST("/pesalock/v1/agreement/submit-payment")
    suspend fun orderPayment(@Body payment: OrderPayment): Response<DetailedAgreementResponse>

    @POST("/pesalock/v1/agreement/decline-payment")
    suspend fun orderDeclinePayment(@Body payment: OrderPayment): Response<DetailedAgreementResponse>

    @GET("/pesalock/v1/notification")
    suspend fun fetchNotifications(): Notification

}