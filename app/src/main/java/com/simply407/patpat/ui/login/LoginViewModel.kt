package com.simply407.patpat.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply407.patpat.data.model.GetUserInfoResponse
import com.simply407.patpat.data.model.LoginRequest
import com.simply407.patpat.data.model.LoginResponse
import com.simply407.patpat.data.repository.LoginRepository
import com.simply407.patpat.data.repository.UserInfoRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val loginRepository = LoginRepository()
    private val userInfoRepository = UserInfoRepository()

    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> get() = _loginResult

    private val _logoutResult = MutableLiveData<Boolean>()
    val logoutResult: LiveData<Boolean> get() = _logoutResult

    private val _withdrawalResult = MutableLiveData<Boolean>()
    val withdrawalResult: LiveData<Boolean> get() = _withdrawalResult

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

    fun userLogout(accessToken: String) {
        viewModelScope.launch {
            try {
                val response = loginRepository.userLogout(accessToken)
                Log.d("LoginViewModel", "userLogout response: $response")
                if (response.isSuccessful) {
                    Log.d("LoginViewModel", "Logout 성공: ${response.code()}, body: ${response.body()}")
                    _logoutResult.postValue(true)
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.d("LoginViewModel", "Logout failed: ${response.code()}, errorBody: $errorBody")
                    _logoutResult.postValue(false)
                }
            } catch (e: Exception) {
                Log.d("LoginViewModel", "Logout exception: ${e.message}", e)
                _logoutResult.postValue(false)
            }
        }
    }

    fun userWithdrawal(accessToken: String) {
        viewModelScope.launch {
            try {
                val response = loginRepository.userWithdrawal(accessToken)
                Log.d("LoginViewModel", "userWithdrawal response: $response")
                if (response.isSuccessful) {
                    _withdrawalResult.postValue(true)
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.d("LoginViewModel", "userWithdrawal failed: ${response.code()}, errorBody: $errorBody")
                    _withdrawalResult.postValue(false)
                }
            } catch (e: Exception) {
                Log.d("LoginViewModel", "userWithdrawal exception: ${e.message}", e)
                _withdrawalResult.postValue(false)
            }
        }
    }

}