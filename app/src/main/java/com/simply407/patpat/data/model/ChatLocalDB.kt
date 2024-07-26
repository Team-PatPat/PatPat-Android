package com.simply407.patpat.data.model

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.simply407.patpat.data.Ui_chat

object ChatLocalDB{

    private lateinit var sharedPref: SharedPreferences

    fun init(context: Context) {
        sharedPref = context.getSharedPreferences("CHAT_DB", Context.MODE_PRIVATE)
    }

    fun getChatList(key: String): MutableList<Ui_chat>{
        val json = sharedPref.getString(key, null)
        val type = object : TypeToken<MutableList<Ui_chat>>() {}.type
        return Gson().fromJson(json, type) ?: mutableListOf()
    }

    fun saveChatList(key: String, chatList: List<Ui_chat>) {
        val json = Gson().toJson(chatList)
        sharedPref.edit().putString(key, json).apply()
    }

    fun deleteChatList(key : String){
        sharedPref.edit().remove(key).apply()
    }
}