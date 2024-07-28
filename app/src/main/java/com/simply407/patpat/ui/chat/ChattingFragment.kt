package com.simply407.patpat.ui.chat
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import com.simply407.patpat.data.ChatGetAll
import com.simply407.patpat.data.MessageGet
import com.simply407.patpat.databinding.FragmentChattingBinding
//import com.simply407.patpat.ui.chat.ChattingViewModel.Companion
//import com.simply407.patpat.ui.chat.ChattingViewModel.Companion.patApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChattingFragment : Fragment() {

    private lateinit var binding : FragmentChattingBinding

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

    private lateinit var roomName : String
    private lateinit var counselorId : String
    private var imageResource=0


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d("Why So Serious",savedInstanceState.toString())
       val patApi = RetrofitInstance.getInstance().create(patApi::class.java)

        binding=FragmentChattingBinding.inflate(inflater,container,false)
        viewModel= ViewModelProvider(requireActivity())[ChattingViewModel::class.java]

    //        settingAdapter()//recycler view 생성
        if (savedInstanceState == null) {
            // Fragment가 처음 생성된 경우에만 API 호출
            roomName=viewModel.getRoomName() //이름
            counselorId=viewModel.getCounselorId()
            imageResource=getThemeResource(roomName)
            requestGetChat(patApi) //getChat api 호출
            settingAdapter()//recycler view 생성
            setupTextChangedListener() //editText관리
            setupSendButtonClickListener() //sendBtn관리
        }
//        setupTextChangedListener() //editText관리
//        setupSendButtonClickListener() //sendBtn관리

        viewModel.items.observe(viewLifecycleOwner, Observer {
            val lastIndex = (binding.chatRv.adapter as? ChattingAdapter)?.itemCount ?: 0
            (binding.chatRv.adapter as? ChattingAdapter)?.updateItems(it.toMutableList())
            binding.chatRv.scrollToPosition(lastIndex)
        })

        viewModel.buttonState.observe(viewLifecycleOwner,Observer{
            if(it==1 || it==3){
                if(it==1){
                    binding.chatSendbtn.setBackgroundResource(R.drawable.chat_btn_load)
                    binding.chatInput.isFocusable=true
                }else{
                    binding.chatSendbtn.setBackgroundResource(R.drawable.chat_btn_pause)
                    binding.chatInput.isFocusable=false

                }
                binding.chatSendbtn.isEnabled=true

            }else{
                binding.chatSendbtn.isEnabled=false
                binding.chatSendbtn.setBackgroundResource(R.drawable.chat_btn_block)

            }
        })


//      ChatLocalDB.init(requireContext())
        return binding.root
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

    private fun settingAdapter() {
        rvAdapter = ChattingAdapter(requireContext(),chatlist)
        binding.chatRv.adapter=rvAdapter
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.stackFromEnd = true
        binding.chatRv.layoutManager = layoutManager

        binding.chatRv.itemAnimator = null
        //(binding.chatRv.adapter as? ChattingAdapter)?.updateItems(chatlist.toMutableList())//첫문장

        //초기 recyclerView 위치 셋팅
        val recyclerViewLayoutParams = binding.chatRv.layoutParams
        recyclerViewLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        binding.chatRv.layoutParams = recyclerViewLayoutParams
    }

    private fun setupTextChangedListener() {
        binding.chatInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                viewModel.activeBtn(2)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty())  viewModel.activeBtn(2)
                else viewModel.activeBtn(1)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun setupSendButtonClickListener() {
        val patApi = RetrofitInstance.getInstance().create(patApi::class.java)
        binding.chatSendbtn.setOnClickListener {
            val chatMessage = binding.chatInput.text.toString()
            if (chatMessage.isNotEmpty()) {
                viewModel.addItem(Ui_chat(true, imageResource, chatMessage))
                binding.chatInput.setText("") // 입력 필드 초기화

                requestPostChatSend(patApi,chatMessage)


                binding.chatSendbtn.setBackgroundResource(R.drawable.chat_btn_block)
            }
        }
    }

    private fun requestGetChat(patApi : patApi){
        patApi.getChat(counselorId).enqueue(object : Callback<ChatGet> {
            override fun onResponse(call: Call<ChatGet>, response: Response<ChatGet>) {
                Log.d("WhatsWrong",response.toString())
                val responsebody =response.body()

                if (responsebody != null) {
                    viewModel.addItem(Ui_chat(false,getThemeResource(roomName), responsebody.counselor.greeting))
                    requestGetChatSend(patApi)
                }
            }
            override fun onFailure(call: Call<ChatGet>, response: Throwable) {
                Toast.makeText(context,"fail to connect server ",Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun requestGetChatSend(patApi : patApi){
        patApi.getChatSend(counselorId).enqueue(object : Callback<ChatGetAll>{
            override fun onResponse(p0: Call<ChatGetAll>, response: Response<ChatGetAll>) {
                val response=response.body()
                val isMessagesEmpty= response?.data?.isNotEmpty()
                Log.d("whatsWrong",response.toString())
                if (isMessagesEmpty == true) {
                    for(i in response.data.size-1 downTo 0){
                        if(response.data[i].role=="USER")
                            viewModel.addItem(Ui_chat(true, imageResource, response.data[i].content))
                        else
                            viewModel.addItem(Ui_chat(false, imageResource, response.data[i].content))
                    }
                }

            }

            override fun onFailure(p0: Call<ChatGetAll>, response: Throwable) {}

        })
    }

    private fun requestPostChatSend(patApi: patApi, message: String) {
        binding.chatInput.hint = "답변을 기다려주세요!"
        viewModel.activeBtn(2)

        patApi.postChatSend(counselorId, messageBody(message))
            .enqueue(object : Callback<ChatSse> {
                override fun onResponse(call: Call<ChatSse>, response: Response<ChatSse>?) {

                    viewModel.activeBtn(1)
                    binding.chatInput.hint = "어떤 고민이 있나요?"

                    val response= response?.body()

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
