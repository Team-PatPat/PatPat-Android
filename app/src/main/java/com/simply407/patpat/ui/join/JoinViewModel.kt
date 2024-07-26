package com.simply407.patpat.ui.join

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply407.patpat.data.model.GetUserInfoResponse
import com.simply407.patpat.data.model.NewUserInfo
import com.simply407.patpat.repository.UserInfoRepository
import kotlinx.coroutines.launch

class JoinViewModel : ViewModel() {

    private val userInfoRepository = UserInfoRepository()

    private val _nickName = MutableLiveData<String>()
    val nickName: LiveData<String> get() = _nickName

    private val _userInfoResult = MutableLiveData<Result<GetUserInfoResponse>>()
    val userInfoResult: LiveData<Result<GetUserInfoResponse>> get() = _userInfoResult

    fun setName(newName: String) {
        _nickName.value = newName
    }

    fun putUserInfo(accessToken: String, newUserInfo: NewUserInfo) {
        viewModelScope.launch {
            try {
                val response = userInfoRepository.putUserInfo(accessToken, newUserInfo)
                Log.d("JoinViewModel", "putUserInfo 응답: $response")
                if (response.isSuccessful) {
                    _userInfoResult.postValue(Result.success(response.body()!!))
                } else {
                    _userInfoResult.postValue(Result.failure(Throwable(response.errorBody()?.string())))
                }
            } catch (e: Exception) {
                _userInfoResult.postValue(Result.failure(e))
            }
        }
    }

}