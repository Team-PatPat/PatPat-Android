package com.simply407.patpat.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
//    private const val URL ="https://oognuyh.asuscomm.com/"
//
//    private val client = Retrofit.Builder().baseUrl(URL).client(getInterceptor())
//        .addConverterFactory(GsonConverterFactory.create()).build()
//
//    private fun getInterceptor(): OkHttpClient {
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(Interceptor()) // 여기서 AuthInterceptor를 추가
//            .build()
//        return okHttpClient
//    }
//
//    fun getInstance() : Retrofit{ return client}

    private const val BASE_URL = "https://oognuyh.asuscomm.com/"

    private val client = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstance(): Retrofit {
        return client
    }


}