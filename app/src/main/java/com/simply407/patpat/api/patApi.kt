package com.simply407.patpat.api

import com.simply407.patpat.data.ChatGet
import com.simply407.patpat.data.ChatSse

import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface patApi {

    @GET("api/v1/chats/{counselorId}")
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjgwYzEzYmI3LTBiNzgtNGViYS1hZWYwLTliOGU2NGY2ZWE0MyIsImVtYWlsIjoidGVzdGVyQHBhdHBhdC5jb20iLCJuYW1lIjoidGVzdGVyIiwiYXZhdGFyVXJsIjpudWxsLCJpYXQiOjE3MjE5MTIyNDAsImV4cCI6MTczOTkxMjI0MH0.NFFD8mQ47m6MX_slJZP4T3bu98tgBjRzsSkkIuMT2fI")
    fun getChat(
        @Path("counselorId") counselorId : String
    ) : Call<ChatGet>

    @GET("/api/v1/counselors")
    fun getCounselor(

    ) :Call<Void>

    @GET("api/v1/chats/{counselorId}/messages")
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjgwYzEzYmI3LTBiNzgtNGViYS1hZWYwLTliOGU2NGY2ZWE0MyIsImVtYWlsIjoidGVzdGVyQHBhdHBhdC5jb20iLCJuYW1lIjoidGVzdGVyIiwiYXZhdGFyVXJsIjpudWxsLCJpYXQiOjE3MjE5MTIyNDAsImV4cCI6MTczOTkxMjI0MH0.NFFD8mQ47m6MX_slJZP4T3bu98tgBjRzsSkkIuMT2fI")
    fun getChatSend(
        @Path("counselorId") counselorId : String,
    ) :Call<Void>

    @POST("api/v1/chats/{counselorId}/messages")
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjgwYzEzYmI3LTBiNzgtNGViYS1hZWYwLTliOGU2NGY2ZWE0MyIsImVtYWlsIjoidGVzdGVyQHBhdHBhdC5jb20iLCJuYW1lIjoidGVzdGVyIiwiYXZhdGFyVXJsIjpudWxsLCJpYXQiOjE3MjE5MTIyNDAsImV4cCI6MTczOTkxMjI0MH0.NFFD8mQ47m6MX_slJZP4T3bu98tgBjRzsSkkIuMT2fI")
    fun postChatSend(
        @Path("counselorId") counselorId : String,
        @Body body : bodyy
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

    data class bodyy(
        val message : String
    )
}

