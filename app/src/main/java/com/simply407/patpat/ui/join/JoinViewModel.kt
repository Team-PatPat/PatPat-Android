package com.simply407.patpat.ui.join

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JoinViewModel : ViewModel() {

    private val _nickName = MutableLiveData<String>()
    val nickName: LiveData<String> get() = _nickName

    fun setName(newName: String) {
        _nickName.value = newName
    }

}