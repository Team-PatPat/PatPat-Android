package com.simply407.patpat.ui.chatting

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.simply407.patpat.R
import com.simply407.patpat.databinding.FragmentChatting2Binding
import com.simply407.patpat.databinding.ItemSaveLetterBinding
import com.simply407.patpat.ui.main.MainActivity


class ChattingFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentChatting2Binding

    private var currentPageIndex: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentChatting2Binding.inflate(layoutInflater, container, false)

        currentPageIndex = arguments?.getInt("currentPageIndex") ?: 0

        initUi()

        return binding.root
    }

    private fun initUi() {
        binding.run {
            mainActivity.hideBottomNavigation()

            materialToolbarChatting.run {
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.CHATTING_FRAGMENT)
                }

                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.letter_chatting_menu_item -> {
                            letterShowDialog()
                            true
                        }

                        else -> false
                    }
                }

                when (currentPageIndex) {
                    0 -> {
                        subtitle = "복남이"
                    }
                    1 -> {
                        subtitle = "닥터 냉철한"
                    }
                    2 -> {
                        subtitle = "곽두팔"
                    }
                    3 -> {
                        subtitle = "코코"
                    }
                }
            }

            editTextChatting.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.isNullOrEmpty()) {
                        buttonChatting.setImageResource(R.drawable.ic_send_off)
                    } else {
                        buttonChatting.setImageResource(R.drawable.ic_send_on)
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

    private fun letterShowDialog() {
        val itemSaveLetterBinding = ItemSaveLetterBinding.inflate(layoutInflater)
        val builder = MaterialAlertDialogBuilder(mainActivity)
        builder.setView(itemSaveLetterBinding.root)
        val dialog = builder.create()

        val name = "복남이"
        val prompt = getString(R.string.save_letter_prompt, name)
        itemSaveLetterBinding.textViewSaveLetterItem.text = prompt

        // 네 좋아요!
        itemSaveLetterBinding.buttonYesSaveLetterItem.setOnClickListener {
            dialog.dismiss()
        }

        // 대화 더 할래요
        itemSaveLetterBinding.buttonNoSaveLetterItem.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainActivity.showBottomNavigation()
    }

}
