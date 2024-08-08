package com.simply407.patpat.data.repository


import com.simply407.patpat.data.api.RetrofitInstance
import com.simply407.patpat.data.model.LoginRequest

class LoginRepository {

    val patApi = RetrofitInstance.getInstance().create(com.simply407.patpat.data.api.patApi::class.java)

    suspend fun postLogin(loginRequest: LoginRequest) = patApi.postLogin(loginRequest)

    suspend fun userLogout(accessToken: String) = patApi.userLogout(accessToken)

    suspend fun userWithdrawal(accessToken: String) = patApi.userWithdrawal(accessToken)
}