package com.simply407.patpat.localDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.simply407.patpat.data.Ui_chat

@Entity(tableName = "chat_table")
data class ChatEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "counselorId")
    val counselorId: String,

    @ColumnInfo(name = "chatMessages")
    val chatMessages: List<Ui_chat>
)