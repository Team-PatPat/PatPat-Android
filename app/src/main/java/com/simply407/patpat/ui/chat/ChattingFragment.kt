package com.simply407.patpat.ui.chat


import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simply407.patpat.R
import com.simply407.patpat.api.RetrofitInstance
import com.simply407.patpat.api.patApi
import com.simply407.patpat.data.ChatGet
import com.simply407.patpat.data.ChatSse
import com.simply407.patpat.data.Ui_chat
import com.simply407.patpat.data.messageBody
import com.simply407.patpat.data.model.ChatLocalDB
import com.simply407.patpat.databinding.FragmentChattingBinding
import kotlinx.coroutines.delay
//import com.simply407.patpat.ui.chat.ChattingViewModel.Companion
//import com.simply407.patpat.ui.chat.ChattingViewModel.Companion.patApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChattingFragment : Fragment() {

    private var _binding : FragmentChattingBinding? = null
    private val binding get()=_binding!!

    private var chatlist= mutableListOf<Ui_chat>() //채팅 list
    private lateinit var recyclerView: RecyclerView
    private lateinit var rvAdapter: ChattingAdapter

    private lateinit var viewModel : ChattingViewModel //ViewModel

    companion object{
        private const val COUNSELORID ="8b5ec154-1346-4f2a-afbc-a83ea62b4288"
        private var initMessage=""

        private const val ROOM1="복남이"
        private const val ROOM2="닥터 냉철한"
        private const val ROOM3="곽두팔"
        private const val ROOM4="코코"

    }



//    private val job = Job()
//    private val scope = CoroutineScope(Dispatchers.Main + job)

    private val imageResource =getThemeResource(ROOM3) //임시코드


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
       val patApi = RetrofitInstance.getInstance().create(patApi::class.java)

        _binding=FragmentChattingBinding.inflate(inflater,container,false)
        viewModel= ViewModelProvider(this)[ChattingViewModel::class.java]

        requestGetChat(patApi) //getChat api 호출

//      ChatLocalDB.init(requireContext())

        //recycler view 생성
        rvAdapter = ChattingAdapter(requireContext(),chatlist)
        binding.chatRv.adapter=rvAdapter
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.stackFromEnd = true
        binding.chatRv.layoutManager = layoutManager

        binding.chatRv.itemAnimator = null
        (binding.chatRv.adapter as? ChattingAdapter)?.updateItems(chatlist.toMutableList())//첫문장

        //초기 recyclerView 위치 셋팅
        val recyclerViewLayoutParams = binding.chatRv.layoutParams
        recyclerViewLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        binding.chatRv.layoutParams = recyclerViewLayoutParams

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTextChangedListener(binding.chatInput, binding.chatSendbtn,viewModel)
        setupSendButtonClickListener(binding.chatInput, binding.chatSendbtn, viewModel) //editText관리 코드

        viewModel.items.observe(viewLifecycleOwner, Observer {
            val lastIndex = (binding.chatRv.adapter as? ChattingAdapter)?.itemCount ?: 0
            (binding.chatRv.adapter as? ChattingAdapter)?.updateItems(it.toMutableList())
            binding.chatRv.scrollToPosition(lastIndex)
        })

        viewModel.buttonState.observe(viewLifecycleOwner,Observer{
            if(it){
                binding.chatSendbtn.isEnabled=true
                binding.chatSendbtn.setBackgroundResource(R.drawable.chat_btn_load)
            }else{
                binding.chatSendbtn.isEnabled=false
                binding.chatSendbtn.setBackgroundResource(R.drawable.chat_btn_block)
            }
        })

    }


    private fun getThemeResource(str :String):Int{ //여기 방 이름 코드입니다
        return when(str){
            ROOM1->1
            ROOM2->2
            ROOM3->3
            else->4
        }
    }

    private fun loadChat() {

    }

    private fun saveChat(){

    }

    private fun setupTextChangedListener(chatInput: EditText, chatSendBtn: ImageButton,viewModel : ChattingViewModel) {
        chatInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                chatSendBtn.isEnabled = false // 버튼 막아두기
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty())  viewModel.activeBtn(false)
                else viewModel.activeBtn(true)
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun setupSendButtonClickListener(chatInput: EditText, chatSendBtn: ImageButton, viewModel: ChattingViewModel) {
        val patApi = RetrofitInstance.getInstance().create(patApi::class.java)

        chatSendBtn.setOnClickListener {
            val chatMessage = chatInput.text.toString()
            if (chatMessage.isNotEmpty()) {
                viewModel.addItem(Ui_chat(true, imageResource, chatMessage))
                chatInput.setText("") // 입력 필드 초기화
                requestPostChatSend(patApi,chatMessage)

                chatSendBtn.isEnabled = false
                chatSendBtn.setBackgroundResource(R.drawable.chat_btn_block)
            }
        }
    }

    private fun requestGetChat(patApi : patApi){
        patApi.getChat(COUNSELORID).enqueue(object : Callback<ChatGet> {
            override fun onResponse(call: Call<ChatGet>, response: Response<ChatGet>) {
                val responsebody =response.body()

                if (responsebody != null) {
                    initMessage=responsebody.counselor.description
                    viewModel.addItem(Ui_chat(false,getThemeResource(ROOM3), initMessage))

                    requestGetChatSend(patApi)
                }
            }
            override fun onFailure(call: Call<ChatGet>, response: Throwable) {
                Toast.makeText(context,"fail to connect server ",Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun requestGetChatSend(patApi : patApi){
        patApi.getChatSend(COUNSELORID,).enqueue(object : Callback<Void>{
            override fun onResponse(p0: Call<Void>, p1: Response<Void>) {

            }

            override fun onFailure(p0: Call<Void>, p1: Throwable) {}

        })
    }

    private fun requestPostChatSend(patApi: patApi, message: String) {
        patApi.postChatSend(COUNSELORID, messageBody(message))
            .enqueue(object : Callback<ChatSse> {
                override fun onResponse(call: Call<ChatSse>, response: Response<ChatSse>?) {
                    // Log.d("UnknwonWhat",response.body()!!.content)
                    val response = response?.body()
                    if (response != null) viewModel.addItem(Ui_chat(false, imageResource, response.content))
                }

                override fun onFailure(call: Call<ChatSse>, response: Throwable) {
                    Log.d("UnknwonWhat", "error")
                }

            })
    }

}

//        binding.root.setOnApplyWindowInsetsListener { _, insets ->
//            val windowInsets = WindowInsetsCompat.toWindowInsetsCompat(insets)
//            val keyboardHeight = windowInsets.getInsets(WindowInsetsCompat.Type.ime())?.bottom ?: 0
//
//            // 키보드가 올라왔을 때 RecyclerView 크기 조정
//            binding.chatRv.layoutParams.height = resources.displayMetrics.heightPixels - keyboardHeight
//            binding.chatRv.requestLayout()
//
//            // 키보드가 올라왔을 때 RecyclerView 스크롤하기
//            val lastItemPosition = binding.chatRv.adapter?.itemCount?.minus(1) ?: 0
//            val lastItemView = binding.chatRv.findViewHolderForAdapterPosition(lastItemPosition)?.itemView
//            if (lastItemView != null) {
//                val location = intArrayOf(0, 0)
//                binding.chatRv.getLocationOnScreen(location)
//                val lastItemBottom = location[1] + lastItemView.height
//                val keyboardTop = binding.root.height
//                if (lastItemBottom > keyboardTop) {
//                    binding.chatRv.smoothScrollToPosition(lastItemPosition)
//                }
//            }
//
//            insets
//        }
