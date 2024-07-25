package com.simply407.patpat.ui.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply407.patpat.api.RetrofitInstance
import com.simply407.patpat.api.patApi
import com.simply407.patpat.data.ChatGet
import com.simply407.patpat.data.ChatSse
import com.simply407.patpat.data.Ui_chat
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChattingViewModel : ViewModel() {

//    companion object{
//        const val SIGNAL_MESSAGE ="입력중.."
//        val patApi = RetrofitInstance.getInstance().create(patApi::class.java)
//        private const val COUNSELORID ="8b5ec154-1346-4f2a-afbc-a83ea62b4288"
//    }

    private val currentItems: MutableList<Ui_chat> = mutableListOf()

    private val _items = MutableLiveData<List<Ui_chat>>(currentItems)
    val items : LiveData<List<Ui_chat>> get()=_items

//    private val _isProcessing=MutableLiveData<Boolean>(true)
//    val isProcessing : LiveData<Boolean> get()=_isProcessing

    private val _buttonState=MutableLiveData<Boolean>(true)
    val buttonState : LiveData<Boolean> get()=_buttonState
    private val stackMessage=""



//    private val _text=MutableLiveData<String>("")
//    val text : LiveData<String> get()=_text


    fun addItem(items : Ui_chat) {
        val hi : com.simply407.patpat.api.patApi.bodyy = com.simply407.patpat.api.patApi.bodyy(items.message)
        currentItems.add(items)
        _items.value = currentItems.toList()
        _buttonState.value=true

    }

//    fun setItem(items: Ui_chat){
//        _isProcessing.value=false
//
//        viewModelScope.launch {
//            while (isActive) {
//
//                delay(1000)
//
//                var lastValue = currentItems[currentItems.size - 1].message
//                if (lastValue == SIGNAL_MESSAGE) { lastValue = "" }
//
//                if(items.message=="FINISH")break
//
//                lastValue += items.message
//                currentItems[currentItems.size - 1] = items.copy(message = lastValue)
//                _items.value = currentItems
//
//                _isProcessing.value = false
//            }
//
//        }
//
//        _isProcessing.value = false
//    }


//    if(items.isUser){
//        patApi.postChatSend(COUNSELORID, hi).enqueue(object : Callback<ChatSse> {
//            override fun onResponse(call: Call<ChatSse>, response: Response<ChatSse>) {
//
//                _buttonState.value=false
//
//                val responses=response.body()
//                currentItems.add(items)
//                _items.value = currentItems.toList()
//            }
//
//            override fun onFailure(call: Call<ChatSse>, response: Throwable) {
//                Log.d("chattttting","/{counselorId}/postmessage failure")
//            }
//
//        })
//    }


}