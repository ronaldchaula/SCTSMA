package com.fyp.sctsma.api

import com.fyp.sctsma.repository.AppPrefRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

//this type of class creates a singleton that can be used or accessed without creating an instance of it
private const val BASE_URL = "example.com"
//unauthenticated Client instance
val unAuthenticatedClient = OkHttpClient.Builder()
    .connectTimeout(120, TimeUnit.SECONDS) // Adjust as needed
    .readTimeout(120, TimeUnit.SECONDS)    // Adjust as needed
    .writeTimeout(120, TimeUnit.SECONDS)   // Adjust as needed
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build()
//moshi instance
val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object RetrofitInstance {
  fun authenticatedApiCall(
      appPrefRepository: AppPrefRepository
  ): NetworkApi {
      val authenticatedApi: NetworkApi by lazy {
          val authInterceptor = AuthInterceptor(appPrefRepository) // Assuming you have an instance of AppPrefRepository
          val client = OkHttpClient.Builder()
              .connectTimeout(120, TimeUnit.SECONDS)
              .readTimeout(120, TimeUnit.SECONDS)
              .writeTimeout(120, TimeUnit.SECONDS)
              .addInterceptor(authInterceptor)
              .addInterceptor(HttpLoggingInterceptor().apply {
                  level = HttpLoggingInterceptor.Level.BODY
              })
              .build()

          Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(MoshiConverterFactory.create(moshi))
              .client(client)
              .build()
              .create(NetworkApi::class.java)
      }
      return authenticatedApi
  }

    //un authenticated api call
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(unAuthenticatedClient)
            .build()
    }

    val unAuthenticatedApi: NetworkApi by lazy {
        retrofit.create(NetworkApi::class.java)
    }

}