package com.simply407.patpat.repository

import com.simply407.patpat.api.RetrofitInstance

class CounselorRepository {

    val patApi = RetrofitInstance.getInstance().create(com.simply407.patpat.api.patApi::class.java)

    suspend fun getCounselors(accessToken: String) = patApi.getCounselors(accessToken)
}