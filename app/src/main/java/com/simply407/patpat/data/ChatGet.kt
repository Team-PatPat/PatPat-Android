package com.simply407.patpat.data

import com.google.gson.annotations.SerializedName

data class ChatGet (

    @SerializedName("id")
    val id : String, //ㅕuser Id
    @SerializedName("counselor")
    val counselor : MessageGet
)

data class MessageGet(
    @SerializedName("name")
    val name : String,
    @SerializedName("description")
    val description :String,
    @SerializedName("greeting")
    val greeting : String

)
