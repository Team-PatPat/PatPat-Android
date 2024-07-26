package com.simply407.patpat.repository

import com.simply407.patpat.api.RetrofitInstance
import com.simply407.patpat.data.model.NewUserInfo

class UserInfoRepository {

    val patApi = RetrofitInstance.getInstance().create(com.simply407.patpat.api.patApi::class.java)

    suspend fun getUserInfo(accessToken: String) = patApi.getUserInfo(accessToken)

    suspend fun putUserInfo(accessToken: String, newUserInfo: NewUserInfo) =
        patApi.putUserInfo(accessToken, newUserInfo)
}