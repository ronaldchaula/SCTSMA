package com.fyp.sctsma.model.agreements

data class Agreement(
    val rowId: String?,
    val productName: String?,
    val description: String?,
    val amount: Double?,
    val quantity: Int?,
    val ownerFullName: String?,
    val ownerLockNumber: String?,
    val ownerPhoneNumber: String?,
    val pickupCenter: String? = null,
    val origin: String? = null,
    val destination: String? = null,
    val targetPerson: String?,
    val agreementCreatedAt: String?,
    val status: String?,
    val paymentStatus: String?,
    val cancelled: Boolean?
)
