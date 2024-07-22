package com.simply407.patpat.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val URL ="unknown"

    private val client = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    fun getInstance() : Retrofit{ return client}


}