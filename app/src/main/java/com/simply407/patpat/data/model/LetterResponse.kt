package com.simply407.patpat.data.model

import com.google.gson.annotations.SerializedName

data class LetterResponse (
   @SerializedName("id") val id: String,
   @SerializedName("counselorId") val counselorId: String,
   @SerializedName("content") val content : String,
   @SerializedName("footer") val footer : String?
)



