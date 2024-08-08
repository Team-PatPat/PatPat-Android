package com.simply407.patpat.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply407.patpat.data.model.GetCounselorResponse
import com.simply407.patpat.data.repository.CounselorRepository
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val counselorRepository = CounselorRepository()

    private val _counselorList = MutableLiveData<Result<List<GetCounselorResponse>>>()
    val counselorList: LiveData<Result<List<GetCounselorResponse>>> get() = _counselorList

    fun getCounselors(accessToken: String) {
        viewModelScope.launch {
            try {
                val response = counselorRepository.getCounselors(accessToken)
                Log.d("HomeViewModel", "getCounselors 응답: $response")
                if (response.isSuccessful) {
                    _counselorList.postValue(Result.success(response.body()!!))
                } else {
                    _counselorList.postValue(Result.failure(Exception("Error: ${response.code()}")))
                }
            } catch (e: Exception) {
                _counselorList.postValue(Result.failure(e))
            }
        }

    }

}