package com.simply407.patpat.data.model

data class PostMessageResponse(
    val id: String,
    val chatId: String,
    val role: String,
    val status: String,
    val type: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String
)
