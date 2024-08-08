package com.simply407.patpat.data.api

import com.simply407.patpat.data.model.ChattingRoomInfo
import com.simply407.patpat.data.model.CreateLetterRequest
import com.simply407.patpat.data.model.CreateLetterResponse
import com.simply407.patpat.data.model.GetAllLettersResponse
import com.simply407.patpat.data.model.GetCounselorResponse
import com.simply407.patpat.data.model.GetUserInfoResponse
import com.simply407.patpat.data.model.LikeLetterRequest
import com.simply407.patpat.data.model.LoginRequest
import com.simply407.patpat.data.model.LoginResponse
import com.simply407.patpat.data.model.MessageInfo
import com.simply407.patpat.data.model.NewUserInfo
import com.simply407.patpat.data.model.PostMessageRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface patApi {

    @POST("api/v1/login")
    suspend fun postLogin(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("api/v1/users/me")
    suspend fun getUserInfo(@Header("Authorization") accessToken: String): Response<GetUserInfoResponse>

    @PUT("api/v1/users/me")
    suspend fun putUserInfo(
        @Header("Authorization") accessToken: String,
        @Body newUserInfo: NewUserInfo
    ): Response<GetUserInfoResponse>

    @GET("api/v1/counselors")
    suspend fun getCounselors(@Header("Authorization") accessToken: String): Response<List<GetCounselorResponse>>

    @GET("api/v1/chats/{counselorId}/messages")
    suspend fun getAllChattingRoomInfo(
        @Header("Authorization") accessToken: String,
        @Path("counselorId") counselorId: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ChattingRoomInfo>

    @POST("api/v1/chats/{counselorId}/messages")
    suspend fun postMessage(
        @Header("Authorization") accessToken: String,
        @Path("counselorId") counselorId: String,
        @Body postMessageRequest : PostMessageRequest
    ): Response<MessageInfo>

    @POST("api/v1/letters")
    suspend fun createLetter(
        @Header("Authorization") accessToken: String,
        @Body createLetterRequest: CreateLetterRequest
    ): Response<CreateLetterResponse>

    @GET("api/v1/letters")
    suspend fun getAllLetters(
        @Header("Authorization") accessToken: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("isLiked") isLiked: Boolean
    ): Response<GetAllLettersResponse>

    @PUT("api/v1/letters/{letterId}")
    suspend fun likeLetter(
        @Header("Authorization") accessToken: String,
        @Path("letterId") letterId: String,
        @Body likeLetterRequest: LikeLetterRequest
    ): Response<CreateLetterResponse>

    @POST("api/v1/logout")
    suspend fun userLogout(@Header("Authorization") accessToken: String): Response<Void>

    @DELETE("api/v1/users/me")
    suspend fun userWithdrawal(@Header("Authorization") accessToken: String): Response<Void>
}



