package com.simply407.patpat.ui.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply407.patpat.R
import com.simply407.patpat.api.RetrofitInstance
import com.simply407.patpat.api.patApi
import com.simply407.patpat.data.ChatGet
import com.simply407.patpat.data.ChatSse
import com.simply407.patpat.data.Ui_chat
import com.simply407.patpat.data.messageBody
import com.simply407.patpat.ui.chat.ChattingViewModel.Companion.COUNSELORID
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChattingViewModel : ViewModel() {

    companion object{
        const val SIGNAL_MESSAGE ="입력중.."
        private const val COUNSELORID ="8b5ec154-1346-4f2a-afbc-a83ea62b4288"
    }


    val patApi = RetrofitInstance.getInstance().create(patApi::class.java)
    private val currentItems: MutableList<Ui_chat> = mutableListOf()

    private val _items = MutableLiveData<MutableList<Ui_chat>>(currentItems)
    val items : LiveData<MutableList<Ui_chat>> get()=_items

    private val _buttonState=MutableLiveData<Int>(1)
    val buttonState : LiveData<Int> get()=_buttonState

    private val _edittextState=MutableLiveData<Boolean>(true)
    val edittextState : LiveData<Boolean> get()=_edittextState


    private var roomName = "unknown"
    fun setRoomName(s :String){
        roomName=s
    }
    fun getRoomName(): String {
        return roomName
    }

    private var counselorId =""

    fun setCounselorId(s :String){
        counselorId=s
    }
    fun getCounselorId(): String {
        return counselorId
    }



    fun activeBtn(value : Int){
        if(value==1) _buttonState.value=1 //동작
        else if(value==2) _buttonState.value=2 //비활성
        else _buttonState.value=3 //중지
    }

    fun activeEditText(b : Boolean){
        if(b) _edittextState.value=true
        else _edittextState.value=false
    }

    fun addItem(items: Ui_chat) {
        currentItems.add(items)
        _items.value = currentItems

    }



}


