package com.simply407.patpat.data

import com.google.gson.annotations.SerializedName
import java.time.ZonedDateTime

//data class ChatGetAll (
//    @SerializedName("messages")
//    val messages: List<MessageAll>
//)
//
//data class MessageAll(
//    @SerializedName("id")
//    val id: String,
//    @SerializedName("chatId")
//    val chatId: String,
//    @SerializedName("role")
//    val role : String,
//    @SerializedName("content")
//    val content: String,
//)

data class ChatGetAll (
    @SerializedName("data")
    val data : List<MessageList>
)

data class MessageList(
    @SerializedName("role")
    val role : String,
    @SerializedName("content")
    val content : String
)