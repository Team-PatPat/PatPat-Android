package com.simply407.patpat.repository

import com.simply407.patpat.api.RetrofitInstance
import com.simply407.patpat.data.model.PostMessageRequest

class ChattingRepository {

    val patApi = RetrofitInstance.getInstance().create(com.simply407.patpat.api.patApi::class.java)

    suspend fun getAllChattingRoomInfo(accessToken: String, counselorId: String, page: Int, size: Int) = patApi.getAllChattingRoomInfo(accessToken, counselorId, page, size)

    suspend fun postMessage(
        accessToken: String,
        counselorId: String,
        postMessageRequest: PostMessageRequest
    ) = patApi.postMessage(accessToken, counselorId, postMessageRequest)
}