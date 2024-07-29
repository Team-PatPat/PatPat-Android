package com.simply407.patpat.ui.chatting

import android.os.Bundle
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentChatting2Binding.inflate(layoutInflater, container, false)

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
            }
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
