package com.simply407.patpat.data

data class Requst_chat (
    val userToken : String?,
    val username : String,
    val message : String
) //server로 REQUEST 전용 DATA CLASS