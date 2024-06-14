package com.fyp.sctsma.api

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.concurrent.TimeUnit

//this type of class creates a singleton that can be used or accessed without creating an instance of it
private const val BASE_URL = "https://pesa-lock-app.onrender.com"

val client = OkHttpClient.Builder()
    .connectTimeout(120, TimeUnit.SECONDS) // Adjust as needed
    .readTimeout(120, TimeUnit.SECONDS)    // Adjust as needed
    .writeTimeout(120, TimeUnit.SECONDS)   // Adjust as needed
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build()

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }
    val appApi: NetworkApi by lazy {
        retrofit.create(NetworkApi::class.java)
    }

}