package com.simply407.patpat.data.model

data class GetCounselorResponse (
    val id: String,
    val name: String,
    val description: String,
    val greeting: String,
    val order: Int,
    val tags: List<String>,
    val taskId: String?,
    val createdAt: String,
    val updatedAt: String?
)