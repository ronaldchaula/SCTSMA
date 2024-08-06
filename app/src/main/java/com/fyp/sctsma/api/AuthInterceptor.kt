package com.fyp.sctsma.api

import com.fyp.sctsma.repository.AppPrefRepository
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val appPrefRepository: AppPrefRepository) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val accessToken = appPrefRepository.getAccessData()?.accessToken //gets access token from the repository

        val requestBuilder = originalRequest.newBuilder()
            .method(originalRequest.method, originalRequest.body)

        if (accessToken != null) { // Add header only if token is available
            requestBuilder.header("Authorization", "Bearer $accessToken")
        }

        return chain.proceed(requestBuilder.build())
    }
}