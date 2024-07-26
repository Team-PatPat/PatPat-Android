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

    private val _buttonState=MutableLiveData<Boolean>(true)
    val buttonState : LiveData<Boolean> get()=_buttonState

//    private val _text=MutableLiveData<String>("")
//    val text : LiveData<String> get()=_text


    fun activeBtn(b : Boolean){
        if(b) _buttonState.value=true
        else _buttonState.value=false
    }

    fun addItem(items: Ui_chat) {
        _buttonState.value = false
        currentItems.add(items)
        _items.value = currentItems

    }

//    private fun requestPostChatSend(patApi: patApi, message: String) {
//        patApi.postChatSend(COUNSELORID, messageBody(message))
//            .enqueue(object : Callback<ChatSse> {
//                override fun onResponse(call: Call<ChatSse>, response: Response<ChatSse>?) {
//                    // Log.d("UnknwonWhat",response.body()!!.content)
//
//                    val responseContent = response?.body()?.content
//
//                    if (currentItems.isNotEmpty()) currentItems.removeAt(currentItems.lastIndex)
//
//                    val newChatItem = responseContent?.let { Ui_chat(false, R.drawable.tmp_profile2, it) }
//                    currentItems.add(newChatItem!!)
//
//
//                    // LiveData 업데이트
//                    _items.value = currentItems
//
//
//
//                }
//
//                override fun onFailure(call: Call<ChatSse>, response: Throwable) {
//                    Log.d("UnknwonWhat", "error")
//                }
//
//            })
//    }


}


