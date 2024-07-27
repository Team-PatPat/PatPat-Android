package com.simply407.patpat.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val URL ="http://oognuyh.asuscomm.com/"

    private val client = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(Intercepter()) // 인터셉터 추가
        .build()

    fun getInstance() : Retrofit{ return client}

    fun getInterceptor() :OkHttpClient{return okHttpClient}


}