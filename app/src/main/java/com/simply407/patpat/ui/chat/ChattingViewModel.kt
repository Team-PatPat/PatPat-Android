package com.simply407.patpat.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply407.patpat.data.Ui_chat


class ChattingViewModel : ViewModel() {

    companion object{
        const val SIGNAL_MESSAGE ="입력중.."
    }

    private val currentItems: MutableList<Ui_chat> = mutableListOf()

    private val _items = MutableLiveData<List<Ui_chat>>(currentItems)
    val items : LiveData<List<Ui_chat>> get()=_items

    private val stackMessage=""

    fun addItem(items : Ui_chat) {
        currentItems.add(items)
        _items.value = currentItems.toList()
    }

    fun setItem(items: Ui_chat){

        var lastValue = currentItems[currentItems.size - 1].message
        if (lastValue == SIGNAL_MESSAGE) { lastValue = "" }

        lastValue += items.message

        currentItems[currentItems.size - 1] = items.copy(message = lastValue)
        // LiveData 업데이트
        _items.value = currentItems
    }




}