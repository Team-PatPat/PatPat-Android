package com.simply407.patpat.data.model

data class CreateLetterResponse (
    val id: String,
    val content: String,
    val footer: String?,
    val counselorId: String,
    val userId: String,
    var isLiked: Boolean,
    val createdAt: String,
    val updatedAt: String
)
