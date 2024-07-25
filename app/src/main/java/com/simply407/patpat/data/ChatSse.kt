package com.simply407.patpat.data

import com.google.gson.annotations.SerializedName

//data class Chat_sse(
//    val data: ChatData
//)
//
//data class ChatData(
//    val message: Message_sse?,
//    val event : String,
//    val index: Int,
//    val stopReason: String?
//)
//
//data class Message_sse(
//    val role: String,
//    val content: String
//)

data class ChatSse(
    @SerializedName("role")
    val role : String,
    @SerializedName("content")
    val content: String
)


