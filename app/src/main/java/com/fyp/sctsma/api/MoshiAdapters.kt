package com.fyp.sctsma.api

import com.fyp.sctsma.model.userData.LoginResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object MoshiAdapters {
    //moshi instance
    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    //loginResponseAdapter
    val loginResponseAdapter: JsonAdapter<LoginResponse>? = moshi.adapter(LoginResponse::class.java).lenient()
}