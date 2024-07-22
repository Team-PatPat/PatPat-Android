package com.simply407.patpat.ui.chat

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simply407.patpat.R
import com.simply407.patpat.data.Ui_chat
import com.simply407.patpat.databinding.FragmentChattingBinding

class ChattingFragment : Fragment() {

    private var _binding : FragmentChattingBinding? = null
    private val binding get()=_binding!!

    private var chatlist= mutableListOf<Ui_chat>() //채팅 list
    private lateinit var recyclerView: RecyclerView

    private lateinit var viewModel : ChattingViewModel //ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding=FragmentChattingBinding.inflate(inflater,container,false)
        viewModel=ViewModelProvider(this).get(ChattingViewModel::class.java)

        viewModel.addItem(Ui_chat(false,getProfileImg("bb"),"도섻헌트!!!"))//들어올 때 소개 추후 조건 걸어서 채팅이 시작 할 때 띄우도록
        viewModel.addItem(Ui_chat(false,getProfileImg("aa"),"도섻헌트!!!"))

        //recycler view 생성
        val rvAdapter = ChattingAdapter(requireContext(),chatlist)
        binding.chatRv.adapter=rvAdapter
        binding.chatRv.layoutManager=LinearLayoutManager(requireContext())

        binding.chatInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 채팅 입력란에 변화가 있을 때마다 스크롤 위치를 마지막 채팅 메시지로 이동
                binding.chatRv.scrollToPosition(rvAdapter.itemCount - 1)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageResource =getProfileImg("bb") //임시코드


        binding.chatSendbtn.setOnClickListener {
            val chatMessage = binding.chatInput.text.toString()
            if (chatMessage.isNotEmpty()) {
                viewModel.addItem(Ui_chat(true, imageResource, chatMessage))
                binding.chatInput.setText(" ")
            }
        }

        viewModel.items.observe(viewLifecycleOwner, Observer {
            (binding.chatRv.adapter as ChattingAdapter).updateItems(it.toMutableList())
            //binding.executePendingBindings()
        })


    }

    private fun getProfileImg(title :String) :Int{
        return when(title){
            "aa" -> R.drawable.tmp_profile
            "bb" -> R.drawable.tmp_profile2
            else-> R.drawable.tmp_profile3
        }
    } //방 제목에 따라 프로필 사진 부여





}