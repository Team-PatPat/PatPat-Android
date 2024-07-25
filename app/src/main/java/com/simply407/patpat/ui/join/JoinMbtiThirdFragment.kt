package com.simply407.patpat.ui.join

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.simply407.patpat.R
import com.simply407.patpat.databinding.CommonMbtiBinding
import com.simply407.patpat.databinding.FragmentJoinMbtiThirdBinding


class JoinMbtiThirdFragment : Fragment() {

    private lateinit var joinInfoActivity: JoinInfoActivity
    private lateinit var binding: FragmentJoinMbtiThirdBinding
    private lateinit var commonMbtiBinding: CommonMbtiBinding
    private lateinit var firstMbti: String
    private lateinit var secondMbti: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        joinInfoActivity = activity as JoinInfoActivity
        binding = FragmentJoinMbtiThirdBinding.inflate(layoutInflater, container, false)
        commonMbtiBinding = CommonMbtiBinding.bind(binding.includeJoinMbtiThird.root)
        firstMbti = arguments?.getString("firstMbti") ?: ""
        secondMbti = arguments?.getString("secondMbti") ?: ""

        initUi()
        clickMbti()
        back()
        skip()

        return binding.root
    }

    private fun initUi() {
        binding.includeJoinMbtiThird.editTextFirstJoinMbti.setText(firstMbti)
        binding.includeJoinMbtiThird.editTextSecondJoinMbti.setText(secondMbti)

        commonMbtiBinding.textViewFirstMbti.text = "F"
        commonMbtiBinding.textViewFirstMbti.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        commonMbtiBinding.textViewSecondMbti.text = "T"
        commonMbtiBinding.textViewSecondMbti.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
    }

    private fun clickMbti() {
        commonMbtiBinding.cardViewFirstMbti.setOnClickListener {
            joinInfoActivity.animationMbti(context, commonMbtiBinding.textViewFirstMbti, commonMbtiBinding.textViewSecondMbti) {

                val bundle = Bundle()
                bundle.putString("firstMbti", firstMbti)
                bundle.putString("secondMbti", secondMbti)
                bundle.putString("thirdMbti", "F")

                joinInfoActivity.addFragment(JoinInfoActivity.JOIN_MBTI_FOURTH_FRAGMENT, true, bundle)
            }
        }

        commonMbtiBinding.cardViewSecondMbti.setOnClickListener {
            joinInfoActivity.animationMbti(context, commonMbtiBinding.textViewFirstMbti, commonMbtiBinding.textViewSecondMbti) {

                val bundle = Bundle()
                bundle.putString("firstMbti", firstMbti)
                bundle.putString("secondMbti", secondMbti)
                bundle.putString("thirdMbti", "T")

                joinInfoActivity.addFragment(JoinInfoActivity.JOIN_MBTI_FOURTH_FRAGMENT, true, bundle)
            }
        }
    }

    private fun back() {
        commonMbtiBinding.textViewBackJoinMbti.setOnClickListener {
            joinInfoActivity.removeFragment(JoinInfoActivity.JOIN_MBTI_THIRD_FRAGMENT)
        }
    }

    private fun skip() {
        commonMbtiBinding.textViewSkipJoinMbti.setOnClickListener {
            joinInfoActivity.moveToMain()
        }
    }

}