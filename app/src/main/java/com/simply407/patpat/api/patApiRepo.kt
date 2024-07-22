package com.simply407.patpat.api

import android.database.Observable
import android.util.Log
import com.simply407.patpat.data.Chat_get
import com.simply407.patpat.data.Chat_sse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET

// private val apiService = RetrofitClient.create(ApiService::class.java)
// private val apiRepository = ApiRepository(apiService)

class patApiRepo(private val apiService: patApi) {

    fun getChat(counselorId: String){
        apiService.getChat(counselorId).enqueue(object :Callback<Chat_get>
        {
            override fun onResponse(p0: Call<Chat_get>, p1: Response<Chat_get>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(p0: Call<Chat_get>, p1: Throwable) {
                TODO("Not yet implemented")
            }

        })
    } //미구현


    private var disposable: Disposable? = null //이건 추후 전역 변수에 선언
    fun postChatSend(counselorId: String){
        disposable =apiService.postChatSend(counselorId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    Log.d("patpatpat","${it} success")
                },
                {
                    Log.d("patpatpat","${it }failure")
                }
            )

    }

    fun deleteChat(counselorId: String){
        apiService.deleteChat(counselorId).enqueue(object :Callback<Void>
        {
            override fun onResponse(p0: Call<Void>, p1: Response<Void>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(p0: Call<Void>, p1: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


}