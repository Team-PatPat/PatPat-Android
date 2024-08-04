package com.simply407.patpat.ui.letter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply407.patpat.data.model.CreateLetterRequest
import com.simply407.patpat.data.model.CreateLetterResponse
import com.simply407.patpat.data.model.GetAllLettersResponse
import com.simply407.patpat.repository.LetterRepository
import kotlinx.coroutines.launch

class LetterViewModel: ViewModel() {

    private val letterRepository = LetterRepository()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _allLettersList = MutableLiveData<Result<GetAllLettersResponse>>()
    val allLettersList: LiveData<Result<GetAllLettersResponse>> get() = _allLettersList

    // SingleLiveEvent 사용
    private val _createLetterResult = SingleLiveEvent<Result<CreateLetterResponse>>()
    val createLetterResult: LiveData<Result<CreateLetterResponse>> get() = _createLetterResult

    fun createLetter(accessToken: String, createLetterRequest: CreateLetterRequest) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                val response = letterRepository.createLetter(accessToken, createLetterRequest)
                Log.d("LetterViewModel", "createLetter 응답: $response")

                if (response.isSuccessful) {
                    _createLetterResult.postValue(Result.success(response.body()!!))
                } else {
                    _createLetterResult.postValue(Result.failure(Exception("Error: ${response.errorBody()?.string()}")))
                }
            } catch (e: Exception) {
                _createLetterResult.postValue(Result.failure(e))
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    fun getAllLetters(accessToken: String, page: Int, size: Int, isLiked: Boolean) {
        viewModelScope.launch {
            try {
                val response = letterRepository.getAllLetters(accessToken, page, size, isLiked)
                Log.d("LetterViewModel", "getAllLetters 응답: $response")

                if (response.isSuccessful) {
                    _allLettersList.postValue(Result.success(response.body()!!))
                } else {
                    _allLettersList.postValue(Result.failure(Exception("Error: ${response.errorBody()?.string()}")))
                }

            } catch (e: Exception) {
                _allLettersList.postValue(Result.failure(e))
            }
        }
    }

}