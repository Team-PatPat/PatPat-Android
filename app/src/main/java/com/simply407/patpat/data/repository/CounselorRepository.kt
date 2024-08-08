package com.simply407.patpat.data.repository

import com.simply407.patpat.data.api.RetrofitInstance

class CounselorRepository {

    val patApi = RetrofitInstance.getInstance().create(com.simply407.patpat.data.api.patApi::class.java)

    suspend fun getCounselors(accessToken: String) = patApi.getCounselors(accessToken)
}