package com.simply407.patpat.api

import com.simply407.patpat.data.Chat_get
import com.simply407.patpat.data.Chat_sse
import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface patApi {

    @GET("api/v1/chats/{counselorId}")
    fun getChat(
        @Path("counselorId") counselorId : String
    ) : Call<Chat_get>

    @POST("api/v1/chats/{counselorId}/messages")
    fun postChatSend(
        @Path("counselorId") counselorId : String
    ) : Observable<Chat_sse>

    @DELETE("api/v1/chats/{counselorId}/messages")
    fun deleteChat(
        @Path("counselorId") counselorId : String
    ) : Call<Void>
}