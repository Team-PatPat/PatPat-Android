package com.simply407.patpat.data


data class Ui_chat(

    val isUser: Boolean, //유저면 true ai면 false
    val profileImg:Int, // img id
    var message: String //메세지
)

