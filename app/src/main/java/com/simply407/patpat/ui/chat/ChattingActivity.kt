package com.simply407.patpat.ui.chat

import com.simply407.patpat.ui.chat.ChattingViewModel

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.android.material.appbar.AppBarLayout
import com.kakao.sdk.common.util.Utility
import com.simply407.patpat.R
import com.simply407.patpat.api.RetrofitInstance
import com.simply407.patpat.api.patApi
import com.simply407.patpat.data.ChatGet
import com.simply407.patpat.data.letter
import com.simply407.patpat.data.letterMessageBody
import com.simply407.patpat.data.model.LetterResponse
import com.simply407.patpat.data.model.LoginRequest
import com.simply407.patpat.databinding.ActivityChattingBinding
import com.simply407.patpat.ui.chat.ChattingFragment
import com.simply407.patpat.ui.join.JoinInfoActivity
import com.simply407.patpat.ui.letter.LetterActivity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChattingActivity :AppCompatActivity() {

    private lateinit var binding: ActivityChattingBinding
    private lateinit var appbar : AppBarLayout
    private val manager =supportFragmentManager

    private lateinit var viewModel : ChattingViewModel //ViewModel

    lateinit var roomName : String

    // 전달받은 데이터를 저장할 변수
    private lateinit var counselorName : String
    private lateinit var counselorId : String

    companion object {
        private const val ROOM1="복남이"
        private const val ROOM2="닥터 냉철한"
        private const val ROOM3="곽두팔"
        private const val ROOM4="코코"
        private const val COUNSELORID ="8b5ec154-1346-4f2a-afbc-a83ea62b4288"
    }
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel= ViewModelProvider(this)[ChattingViewModel::class.java]

        counselorName = intent.getStringExtra("counselorName") ?: ""
        counselorId = intent.getStringExtra("counselorId") ?: ""

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            insets
        }
        val patApi = RetrofitInstance.getInstance().create(patApi::class.java)
        //roomName= intent.getStringExtra("코코").toString()

        viewModel.addRoomName(ROOM1)
        binding.appbar.appbarChatTitle.text=ROOM1

        //val roomName =intent.getStringExtra("name")

//        if(savedInstanceState==null){
//            Log.d("LetterFragment","Activity")
//            val transaction=manager.beginTransaction()
//            val fragmentChat = ChattingFragment()
//            transaction.replace(R.id.chattingfragment,fragmentChat).addToBackStack(null).commit()
//        }



        binding.appbar.appbarChatStore.setOnClickListener {
            postLetter(patApi)

        }

        binding.appbar.appbarChatBackBtn.setOnClickListener{
            finish()
        }



    }

     fun postLetter( patApi: patApi) {
         val intent = Intent(this@ChattingActivity, LetterActivity::class.java)
        patApi.postLetters(letterMessageBody(COUNSELORID)).enqueue(object : Callback<LetterResponse>{
            override fun onResponse(p0: Call<LetterResponse>, response: Response<LetterResponse>) {
                if(response.isSuccessful){
                    removeData(patApi)

                    val response=response.body()
                    if (response != null) {
                        Log.d("WhatsSuccess",response.toString())

                        //val dclass=letter(ROOM3,"윤성준",response.content.content,0,response.content.footer)
                        intent.putExtra("letter_room", ROOM1)
                        intent.putExtra("letter_user", "윤성준")
                        intent.putExtra("letter_content", response.content)
                        if(response.footer!=null){
                            intent.putExtra("letter_image", "0")
                            intent.putExtra("letter_comment", response.footer)
                        }else{
                            intent.putExtra("letter_image", "Image_file")
                            intent.putExtra("letter_comment", "NO")
                        }

                    }
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(p0: Call<LetterResponse>, error: Throwable) {
                Log.d("WhatsError",error.toString())
            }

        })

    }

    fun removeData(patApi: patApi){
        patApi.deleteChat(COUNSELORID).enqueue(object :Callback<Void>{
            override fun onResponse(p0: Call<Void>, p1: Response<Void>) {
               Log.d("WhatsError",p1.body().toString())
            }

            override fun onFailure(p0: Call<Void>, p1: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


}