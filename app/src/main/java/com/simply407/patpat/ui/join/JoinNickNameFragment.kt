package com.simply407.patpat.ui.join


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.simply407.patpat.R
import com.simply407.patpat.databinding.FragmentJoinNickNameBinding

class JoinNickNameFragment : Fragment() {

    private lateinit var joinInfoActivity: JoinInfoActivity
    private lateinit var binding: FragmentJoinNickNameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        joinInfoActivity = activity as JoinInfoActivity
        binding = FragmentJoinNickNameBinding.inflate(layoutInflater, container, false)

        checkValidText()
        onClickButton()

        return binding.root
    }

    fun checkValidText() {
        binding.textInputLayoutJoinNickName.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Do nothing
            }

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    if (it.length == 15) {
                        binding.textInputLayoutJoinNickName.helperText = "15자 이내로 입력해 주세요."
                    } else {
                        binding.textInputLayoutJoinNickName.helperText = null
                    }

                    if (it.isEmpty()) {
                        binding.buttonCheckJoinComplete.setBackgroundResource(R.drawable.bg_gray_r15)
                    } else{
                        binding.buttonCheckJoinComplete.setBackgroundResource(R.drawable.bg_orange_r15)
                    }
                }
            }
        })
    }

    private fun onClickButton() {
        binding.buttonCheckJoinComplete.setOnClickListener {
            val background = binding.buttonCheckJoinComplete.background
            val orangeBackground = ContextCompat.getDrawable(requireContext(), R.drawable.bg_orange_r15)

            if (background.constantState == orangeBackground?.constantState) {
                joinInfoActivity.addFragment(JoinInfoActivity.JOIN_MBTI_FIRST_FRAGMENT, true, null)
            }
        }
    }

}