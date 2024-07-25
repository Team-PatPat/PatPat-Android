package com.simply407.patpat.ui.chat

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simply407.patpat.R
import com.simply407.patpat.data.Ui_chat
import com.simply407.patpat.databinding.FragmentChattingBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ChattingFragment : Fragment() {

    private var _binding : FragmentChattingBinding? = null
    private val binding get()=_binding!!

    private var chatlist= mutableListOf<Ui_chat>() //채팅 list
    private lateinit var recyclerView: RecyclerView
    private lateinit var rvAdapter: ChattingAdapter

    private lateinit var viewModel : ChattingViewModel //ViewModel

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    private val imageResource =getProfileImg("bb") //임시코드

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding=FragmentChattingBinding.inflate(inflater,container,false)
        viewModel= ViewModelProvider(this)[ChattingViewModel::class.java]
        viewModel.addItem(Ui_chat(false,getProfileImg("bb"),"안녕~"))//들어올 때 소개 추후 조건 걸어서 채팅이 시작 할 때 띄우도록

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

        //위치에 따른 recycler의 움직임
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




        binding.chatSendbtn.setOnClickListener {
            val chatMessage = binding.chatInput.text.toString()
            if (chatMessage.isNotEmpty()) {
                viewModel.addItem(Ui_chat(true, imageResource, chatMessage))
                binding.chatInput.setText(" ")
                viewModel.addItem(Ui_chat(false, imageResource, "입력중.."))

            }
        }


        val lastIndex = (binding.chatRv.adapter as? ChattingAdapter)?.itemCount ?: 0
        viewModel.items.observe(viewLifecycleOwner, Observer {
            (binding.chatRv.adapter as? ChattingAdapter)?.updateItems(it.toMutableList())
            binding.chatRv.scrollToPosition(lastIndex)
        })




    }
    private fun getProfileImg(title :String) :Int{
        return when(title){
            "aa" -> R.drawable.tmp_profile
            "bb" -> R.drawable.tmp_profile2
            else-> R.drawable.tmp_profile3
        }
    } //방 제목에 따라 프로필 사진 부여

    private fun sendMessage() {
        val message = "아"
        viewModel.setItem(Ui_chat(false,imageResource,message))
    }


}