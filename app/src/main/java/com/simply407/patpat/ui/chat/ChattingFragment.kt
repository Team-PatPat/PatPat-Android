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
import com.simply407.patpat.data.model.ChatLocalDB
import com.simply407.patpat.databinding.FragmentChattingBinding
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
        private var initialMent =""
    }


//    private val job = Job()
//    private val scope = CoroutineScope(Dispatchers.Main + job)

    private val imageResource =getProfileImg("bb") //임시코드


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


//        patApi.getCounselor().enqueue(object :Callback<Void>{
//            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                Log.d()
//            }
//
//            override fun onFailure(call: Call<Void>, response: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
        patApi.getChat(COUNSELORID).enqueue(object : Callback<ChatGet> {
            override fun onResponse(call: Call<ChatGet>, response: Response<ChatGet>) {

                Log.d("chattttting","${response}")

                val response=response.body()
                //Log.d("chattttting","/{counselorId}  ${response.toString()}")
                if (response != null) {
                    initialMent=response.counselor.description
                    viewModel.addItem(Ui_chat(false,getProfileImg("bb"), initialMent))
                }

                patApi.getChatSend(COUNSELORID,).enqueue(object : Callback<Void>{
                    override fun onResponse(p0: Call<Void>, p1: Response<Void>) {
                        Log.d("chattttting","/{counselorId}/message ${response.toString()}")
                    }

                    override fun onFailure(p0: Call<Void>, p1: Throwable) {
                        Log.d("chattttting","/{counselorId}/message failure")
                    }

                })
            }
            override fun onFailure(call: Call<ChatGet>, response: Throwable) {
                Log.d("chattttting","/{counselorId} failure")
            }
        })




//        ChatLocalDB.init(requireContext())

        //recycler view 생성
        rvAdapter = ChattingAdapter(requireContext(),chatlist)
        binding.chatRv.adapter=rvAdapter
        val layoutManager = LinearLayoutManager(requireContext())
        layoutManager.stackFromEnd = true
        binding.chatRv.layoutManager = layoutManager
        binding.chatRv.itemAnimator = null

        //초기 recyclerView 위치 셋팅
        val recyclerViewLayoutParams = binding.chatRv.layoutParams
        recyclerViewLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        binding.chatRv.layoutParams = recyclerViewLayoutParams

        binding.root.setOnApplyWindowInsetsListener { _, insets ->
            val windowInsets = WindowInsetsCompat.toWindowInsetsCompat(insets)
            val keyboardHeight = windowInsets.getInsets(WindowInsetsCompat.Type.ime())?.bottom ?: 0

            // 키보드가 올라왔을 때 RecyclerView 크기 조정
            binding.chatRv.layoutParams.height = resources.displayMetrics.heightPixels - keyboardHeight
            binding.chatRv.requestLayout()

            // 키보드가 올라왔을 때 RecyclerView 스크롤하기
            val lastItemPosition = binding.chatRv.adapter?.itemCount?.minus(1) ?: 0
            val lastItemView = binding.chatRv.findViewHolderForAdapterPosition(lastItemPosition)?.itemView
            if (lastItemView != null) {
                val location = intArrayOf(0, 0)
                binding.chatRv.getLocationOnScreen(location)
                val lastItemBottom = location[1] + lastItemView.height
                val keyboardTop = binding.root.height
                if (lastItemBottom > keyboardTop) {
                    binding.chatRv.smoothScrollToPosition(lastItemPosition)
                }
            }

            insets
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupChatInputListener(binding.chatInput, binding.chatSendbtn, viewModel)


        viewModel.items.observe(viewLifecycleOwner, Observer {
            val lastIndex = (binding.chatRv.adapter as? ChattingAdapter)?.itemCount ?: 0
            (binding.chatRv.adapter as? ChattingAdapter)?.updateItems(it.toMutableList())
            binding.chatRv.scrollToPosition(lastIndex)
        })

        viewModel.buttonState.observe(viewLifecycleOwner,Observer{
            binding.chatSendbtn.isEnabled = it
        })




    }
    private fun getProfileImg(title :String) :Int{
        return when(title){
            "aa" -> R.drawable.tmp_profile
            "bb" -> R.drawable.tmp_profile2
            else-> R.drawable.tmp_profile3
        }
    } //방 제목에 따라 프로필 사진 부여




    private fun loadChat() {

    }

    private fun saveChat(){

    }

    private fun setupChatInputListener(chatInput: EditText, chatSendBtn: ImageButton, viewModel: ChattingViewModel) {
        val patApi = RetrofitInstance.getInstance().create(patApi::class.java)
        chatInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                chatSendBtn.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) {
                    chatSendBtn.isEnabled = true
                    chatSendBtn.setBackgroundResource(R.drawable.chat_setbtn)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        chatSendBtn.setOnClickListener {
            val chatMessage = chatInput.text.toString()
            if (chatMessage.isNotEmpty()) {
                viewModel.addItem(Ui_chat(true, imageResource, chatMessage))

                chatInput.setText(" ")
                val hi : com.simply407.patpat.api.patApi.bodyy = com.simply407.patpat.api.patApi.bodyy(chatMessage)
                patApi.postChatSend(COUNSELORID, hi).enqueue(object : Callback<ChatSse> {
                    override fun onResponse(call: Call<ChatSse>, response: Response<ChatSse>) {
                        val responses=response.body()

                        if (responses != null) {
                            viewModel.addItem(Ui_chat(false, imageResource, responses.content))
                        }
                    }

                    override fun onFailure(call: Call<ChatSse>, response: Throwable) {
                        Log.d("chattttting","/{counselorId}/postmessage failure")
                    }

                })

                chatSendBtn.isEnabled = false
                chatSendBtn.setBackgroundResource(R.drawable.chat_sendbtn_lock)
            }
        }
    }


}