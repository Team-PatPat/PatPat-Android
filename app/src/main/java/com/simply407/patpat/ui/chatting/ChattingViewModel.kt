package com.simply407.patpat.ui.chatting

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply407.patpat.data.model.ChattingRoomInfo
import com.simply407.patpat.data.model.PostMessageRequest
import com.simply407.patpat.data.model.PostMessageResponse
import com.simply407.patpat.repository.ChattingRepository
import kotlinx.coroutines.launch

class ChattingViewModel : ViewModel() {

    private val chattingRepository = ChattingRepository()

    private val _chattingRoomInfo = MutableLiveData<Result<ChattingRoomInfo>>()
    val chattingRoomInfo: LiveData<Result<ChattingRoomInfo>> get() = _chattingRoomInfo

    private val _postMessageResult = MutableLiveData<Result<PostMessageResponse>>()
    val postMessageResult: LiveData<Result<PostMessageResponse>> get() = _postMessageResult

    fun getAllChattingRoomInfo(accessToken: String, counselorId: String, page: Int, size: Int) {
        viewModelScope.launch {
            try {
                val response = chattingRepository.getAllChattingRoomInfo(accessToken, counselorId, page, size)
                Log.d("ChattingViewModel", "checkChattingRoom 응답: $response")
                if (response.isSuccessful) {
                    _chattingRoomInfo.postValue(Result.success(response.body()!!))
                } else {
                    _chattingRoomInfo.postValue(Result.failure(Exception("Error: ${response.errorBody()?.string()}")))
                }
            } catch (e: Exception) {
                Log.d("ChattingViewModel", "checkChattingRoom 에러: $e")
                _chattingRoomInfo.postValue(Result.failure(e))
            }
        }
    }

    fun postMessage(accessToken: String, counselorId : String, postMessageRequest : PostMessageRequest) {
        viewModelScope.launch {
            try {
                val response = chattingRepository.postMessage(accessToken, counselorId, postMessageRequest)
                Log.d("ChattingViewModel", "postMessage 응답: $response")
                Log.d("ChattingViewModel", "postMessage 응답 body(): ${response.body()}")
                if (response.isSuccessful) {
                    _postMessageResult.postValue(Result.success(response.body()!!))
                } else {
                    _postMessageResult.postValue(Result.failure(Exception("Error: ${response.errorBody()?.string()}")))
                }

            } catch (e: Exception) {
                Log.d("ChattingViewModel", "postMessage 에러: $e")
                _postMessageResult.postValue(Result.failure(e))
            }

        }
    }

}