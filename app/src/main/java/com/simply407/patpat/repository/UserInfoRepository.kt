package com.simply407.patpat.repository

import com.simply407.patpat.api.RetrofitInstance

class UserInfoRepository {

    val patApi = RetrofitInstance.getInstance().create(com.simply407.patpat.api.patApi::class.java)

    suspend fun getUserInfo(accessToken: String) = patApi.getUserInfo(accessToken)
}