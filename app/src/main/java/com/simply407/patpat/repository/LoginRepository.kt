package com.simply407.patpat.repository


import com.simply407.patpat.api.RetrofitInstance
import com.simply407.patpat.data.model.LoginRequest

class LoginRepository {

    val patApi = RetrofitInstance.getInstance().create(com.simply407.patpat.api.patApi::class.java)

    suspend fun postLogin(loginRequest: LoginRequest) = patApi.postLogin(loginRequest)
}