package com.simply407.patpat.localDB

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.simply407.patpat.data.Ui_chat

interface ChatDao {

    @Query("SELECT * FROM chat_table WHERE counselorId = :counselorId")
    fun getAllChat(counselorId :  String) : ChatEntity

    @Query("UPDATE chat_table SET chatMessages = :updateChatMessages WHERE counselorId = :counselorId")
    fun insertChat(counselorId :  String , updateChatMessages : List<Ui_chat>)


    @Query("DELETE FROM chat_table WHERE counselorId = :counselorId")
    fun deleteChat(counselorId: String)
}