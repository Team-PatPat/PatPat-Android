//import android.util.Log
//import com.simply407.patpat.api.patApi
//import com.simply407.patpat.data.Chat_get
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
//import io.reactivex.rxjava3.disposables.Disposable
//import io.reactivex.rxjava3.schedulers.Schedulers
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//object patApiRepo {
//    private lateinit var apiService: patApi
//    private var disposable: Disposable? = null
//
//    fun init(apiService: patApi) {
//        this.apiService = apiService
//    }
//
//    fun getChat(counselorId: String){
//        apiService.getChat(counselorId).enqueue(object : Callback<Chat_get> {
//            override fun onResponse(call: Call<Chat_get>, response: Response<Chat_get>) {
//
//            }
//
//            override fun onFailure(call: Call<Chat_get>, response: Throwable) {
//                // TODO: Implement error handling
//            }
//        })
//    }
//
//    fun postChatSend(counselorId: String, message: String):String {
//
//        var returnString =""
//        disposable = apiService.postChatSend(counselorId, message)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {event->
//                    when(event.data.event){
//                        "token"->{
//                            returnString= event.data.message!!.content
//                        }
//                        "result"->{
//                            returnString= event.data.message!!.content
//                        }
//                        "signal"->{
//                            returnString= "FINISH"
//                        }
//                    }
//
//                },
//                {error->
//
//                }
//            )
//
//        return returnString
//    }
//
//    fun deleteChat(counselorId: String) {
//        apiService.deleteChat(counselorId).enqueue(object : Callback<Void> {
//            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                // TODO: Implement response handling
//            }
//
//            override fun onFailure(call: Call<Void>, response: Throwable) {
//                // TODO: Implement error handling
//            }
//        })
//    }
//}
