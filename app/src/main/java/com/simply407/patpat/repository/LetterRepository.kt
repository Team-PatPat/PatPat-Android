package com.simply407.patpat.repository

import com.simply407.patpat.api.RetrofitInstance
import com.simply407.patpat.data.model.CreateLetterRequest
import com.simply407.patpat.data.model.LikeLetterRequest

class LetterRepository {

    val patApi = RetrofitInstance.getInstance().create(com.simply407.patpat.api.patApi::class.java)

    suspend fun createLetter(accessToken: String, createLetterRequest: CreateLetterRequest) =
        patApi.createLetter(accessToken, createLetterRequest)

    suspend fun getAllLetters(accessToken: String, page: Int, size: Int, isLiked: Boolean) =
        patApi.getAllLetters(accessToken, page, size, isLiked)

    suspend fun likeLetter(
        accessToken: String,
        letterId: String,
        likeLetterRequest: LikeLetterRequest
    ) = patApi.likeLetter(accessToken, letterId, likeLetterRequest)
}