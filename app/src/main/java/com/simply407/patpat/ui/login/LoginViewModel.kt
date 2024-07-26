package com.simply407.patpat.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply407.patpat.data.model.GetUserInfoResponse
import com.simply407.patpat.data.model.LoginRequest
import com.simply407.patpat.data.model.LoginResponse
import com.simply407.patpat.repository.LoginRepository
import com.simply407.patpat.repository.UserInfoRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val loginRepository = LoginRepository()
    private val userInfoRepository = UserInfoRepository()

    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> get() = _loginResult

    private val _userInfoResult = MutableLiveData<Result<GetUserInfoResponse>>()
    val userInfoResult: LiveData<Result<GetUserInfoResponse>> get() = _userInfoResult

    fun postLogin(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {
                val response = loginRepository.postLogin(loginRequest)
                Log.d("LoginViewModel", "postLogin response: $response")
                if (response.isSuccessful) {
                    _loginResult.postValue(Result.success(response.body()!!))
                } else {
                    _loginResult.postValue(Result.failure(Throwable(response.errorBody()?.string())))
                }
            } catch (e: Exception) {
                _loginResult.postValue(Result.failure(e))
            }
        }
    }

    fun getUserInfo(accessToken: String) {
        viewModelScope.launch {
            try {
                val response = userInfoRepository.getUserInfo(accessToken)
                Log.d("LoginViewModel", "getUserInfo response: $response")
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