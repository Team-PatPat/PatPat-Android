package com.simply407.patpat.data

data class Chat_sse(
    val data: ChatData
)

data class ChatData(
    val message: Message_sse?,
    val event : String,
    val index: Int,
    val stopReason: String?
)

data class Message_sse(
    val role: String,
    val content: String
)


