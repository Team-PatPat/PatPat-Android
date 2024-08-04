package com.simply407.patpat.data.model

data class GetAllLettersResponse(
    val data: List<CreateLetterResponse>,
    val totalElements: Int,
    val currentPage: Int,
    val totalPages: Int
)
