package com.simply407.patpat.api

import com.simply407.patpat.data.ChatGet
import com.simply407.patpat.data.ChatSse
import com.simply407.patpat.data.letterMessageBody
import com.simply407.patpat.data.messageBody
import com.simply407.patpat.data.ChatGetAll
import com.simply407.patpat.data.model.ChattingRoomInfo
import com.simply407.patpat.data.model.CreateLetterRequest
import com.simply407.patpat.data.model.CreateLetterResponse
import com.simply407.patpat.data.model.GetAllLettersResponse
import com.simply407.patpat.data.model.GetCounselorResponse
import com.simply407.patpat.data.model.GetUserInfoResponse
import com.simply407.patpat.data.model.LetterResponse
import com.simply407.patpat.data.model.LikeLetterRequest
import com.simply407.patpat.data.model.LoginRequest
import com.simply407.patpat.data.model.LoginResponse
import com.simply407.patpat.data.model.MessageInfo
import com.simply407.patpat.data.model.NewUserInfo
import com.simply407.patpat.data.model.PostMessageRequest

import retrofit2.Call
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


    @GET("api/v1/chats/{counselorId}")
    //@Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjgwYzEzYmI3LTBiNzgtNGViYS1hZWYwLTliOGU2NGY2ZWE0MyIsImVtYWlsIjoidGVzdGVyQHBhdHBhdC5jb20iLCJuYW1lIjoidGVzdGVyIiwiYXZhdGFyVXJsIjpudWxsLCJpYXQiOjE3MjE5MTIyNDAsImV4cCI6MTczOTkxMjI0MH0.NFFD8mQ47m6MX_slJZP4T3bu98tgBjRzsSkkIuMT2fI")
    fun getChat(
        @Path("counselorId") counselorId : String
    ) : Call<ChatGet>

    @GET("/api/v1/counselors")
    fun getCounselor(

    ) :Call<Void>

    @GET("api/v1/chats/{counselorId}/messages")
   // @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjgwYzEzYmI3LTBiNzgtNGViYS1hZWYwLTliOGU2NGY2ZWE0MyIsImVtYWlsIjoidGVzdGVyQHBhdHBhdC5jb20iLCJuYW1lIjoidGVzdGVyIiwiYXZhdGFyVXJsIjpudWxsLCJpYXQiOjE3MjE5MTIyNDAsImV4cCI6MTczOTkxMjI0MH0.NFFD8mQ47m6MX_slJZP4T3bu98tgBjRzsSkkIuMT2fI")
    fun getChatSend(
        @Path("counselorId") counselorId : String,
    ) :Call<ChatGetAll>

    @POST("api/v1/chats/{counselorId}/messages")
    //@Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjgwYzEzYmI3LTBiNzgtNGViYS1hZWYwLTliOGU2NGY2ZWE0MyIsImVtYWlsIjoidGVzdGVyQHBhdHBhdC5jb20iLCJuYW1lIjoidGVzdGVyIiwiYXZhdGFyVXJsIjpudWxsLCJpYXQiOjE3MjE5MTIyNDAsImV4cCI6MTczOTkxMjI0MH0.NFFD8mQ47m6MX_slJZP4T3bu98tgBjRzsSkkIuMT2fI")
    fun postChatSend(
        @Path("counselorId") counselorId : String,
        @Body body : messageBody
    ) : Call<ChatSse>

//    @POST("api/v1/chats/{counselorId}/messages")
//    fun postChatSend(
//        @Path("counselorId") counselorId : String,
//        @Body message: String
//    ) : Observable<Chat_sse>

    @DELETE("api/v1/chats/{counselorId}/messages")
    fun deleteChat(
        @Path("counselorId") counselorId : String
    ) : Call<Void>

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

    @POST("/api/v1/letters")
//    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjgwYzEzYmI3LTBiNzgtNGViYS1hZWYwLTliOGU2NGY2ZWE0MyIsImVtYWlsIjoidGVzdGVyQHBhdHBhdC5jb20iLCJuYW1lIjoidGVzdGVyIiwiYXZhdGFyVXJsIjpudWxsLCJpYXQiOjE3MjE5MTIyNDAsImV4cCI6MTczOTkxMjI0MH0.NFFD8mQ47m6MX_slJZP4T3bu98tgBjRzsSkkIuMT2fI")
    fun postLetters(
        @Body messageBody: letterMessageBody
    ):Call<LetterResponse>

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



