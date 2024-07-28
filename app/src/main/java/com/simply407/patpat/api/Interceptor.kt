package com.simply407.patpat.api

import com.simply407.patpat.data.model.SharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class Interceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val token = SharedPreferencesManager.getUserAccessToken()


        val originalRequest: Request = chain.request()
        val requestBuilder: Request.Builder = originalRequest.newBuilder()


        if (token != null) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}