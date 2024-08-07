package com.simply407.patpat.data.model

data class CounselorMbtiInfo(val name: String, val mbtiList: List<String>)

object MBTIRepository {
    val CounselorMbtiDataList = listOf(
        CounselorMbtiInfo("복남이", listOf("INFJ", "INFP", "ENFJ", "ENFP")),
        CounselorMbtiInfo("닥터 냉철한", listOf("INTJ", "INTP", "ENTJ", "ENTP")),
        CounselorMbtiInfo("곽두팔", listOf("ISTJ", "ISTP", "ESTJ", "ESTP")),
        CounselorMbtiInfo("코코", listOf("ISFJ", "ISFP", "ESFJ", "ESFP"))
    )
}