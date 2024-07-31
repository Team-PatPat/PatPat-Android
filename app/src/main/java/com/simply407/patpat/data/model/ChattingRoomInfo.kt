package com.simply407.patpat.data.model

data class ChattingRoomInfo(
    val data: List<MessageInfo>,
    val totalElements: Int,
    val currentPage: Int,
    val totalPages: Int
)

data class MessageInfo(
    val id: String,
    val chatId: String,
    val role: String,
    val status: String,
    val type: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String
)
