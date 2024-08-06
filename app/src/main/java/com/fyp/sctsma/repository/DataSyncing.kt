//package com.fyp.sctsma.repository
//
//import android.content.Context
//import androidx.work.CoroutineWorker
//import androidx.work.WorkerParameters
//import com.fyp.sctsma.api.RetrofitInstance
//import com.fyp.sctsma.model.userData.User
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//class SyncWorker(
//    context: Context,
//    workerParams: WorkerParameters,
//    private val appPrefRepository: AppPrefRepository
//) : CoroutineWorker(context, workerParams) {
//
//    override suspend fun doWork(): Result {
//        return withContext(Dispatchers.IO) {
//            try {
//                val localData = appPrefRepository.getUserData()
//
//                if (localData != null) {
//                    val response = RetrofitInstance.authenticatedApiCall(appPrefRepository).fetchRemoteUserData()
//
//                    if (response.isSuccessful) {
//                        val remoteData = response.body()
//
//                        if (remoteData != null) {
//                            val updatedData = mergeData(localData, remoteData)
//                            appPrefRepository.saveUserData(updatedData)
//                            RetrofitInstance.authenticatedApiCall(appPrefRepository).updateUserData(updatedData)
//                        }
//                    }
//                }
//
//                Result.success()
//            } catch (e: Exception) {
//                Result.failure()
//            }
//        }
//    }
//
//    private fun mergeData(localData: User, remoteData: User): User {
//        return User(
//            rowId = localData.rowId,
//            username = localData.username,
//            email = localData.email.takeIf { it?.isNotEmpty() == true } ?: remoteData.email,
//            contactName = localData.contactName.takeIf { it?.isNotEmpty() == true } ?: remoteData.contactName,
//            contactEmail = localData.contactEmail.takeIf { it?.isNotEmpty() == true } ?: remoteData.contactEmail,
//            contactPhoneNumber = localData.contactPhoneNumber.takeIf { it.isNotEmpty() } ?: remoteData.contactPhoneNumber,
//            accountType = localData.accountType,
//            lockNumber = localData.lockNumber,
//            firstName = localData.firstName.takeIf { it?.isNotEmpty() == true } ?: remoteData.firstName,
//            lastName = localData.lastName.takeIf { it?.isNotEmpty() == true } ?: remoteData.lastName,
//            dateOfBirth = localData.dateOfBirth.takeIf { it?.isNotEmpty() == true } ?: remoteData.dateOfBirth,
//            gender = localData.gender.takeIf { it?.isNotEmpty() == true } ?: remoteData.gender,
//            nationality = localData.nationality.takeIf { it?.isNotEmpty() == true } ?: remoteData.nationality,
//            addressLine1 = localData.addressLine1.takeIf { it?.isNotEmpty() == true } ?: remoteData.addressLine1,
//            city = localData.city.takeIf { it?.isNotEmpty() == true } ?: remoteData.city,
//            state = localData.state.takeIf { it?.isNotEmpty() == true } ?: remoteData.state,
//            postalCode = localData.postalCode.takeIf { it?.isNotEmpty() == true } ?: remoteData.postalCode,
//            country = localData.country.takeIf { it?.isNotEmpty() == true } ?: remoteData.country,
//            latitude = localData.latitude.takeIf { it?.isNotEmpty() == true } ?: remoteData.latitude,
//            longitude = localData.longitude.takeIf { it?.isNotEmpty() == true } ?: remoteData.longitude,
//            photoUrl = localData.photoUrl.takeIf { it?.isNotEmpty() == true } ?: remoteData.photoUrl,
//            roles = localData.roles
//        )
//    }
//}