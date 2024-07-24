package com.simply407.patpat.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simply407.patpat.data.Ui_chat

class ChattingViewModel : ViewModel() {


    private val currentItems: MutableList<Ui_chat> = mutableListOf()

    private val _items = MutableLiveData<List<Ui_chat>>(currentItems)
    val items : LiveData<List<Ui_chat>> get()=_items

    fun addItem(items : Ui_chat){
        currentItems.add(items)
        _items.value = currentItems.toList()

    }
}