package com.simply407.patpat.data

data class Chat_get (
    val id : String, //ㅕuser Id
    val message : List<Message_get>
)

data class Message_get(
    val id : String,
    val chatId : String,
    val content : String
)
