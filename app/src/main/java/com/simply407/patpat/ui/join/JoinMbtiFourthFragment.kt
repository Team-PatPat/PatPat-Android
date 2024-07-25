package com.simply407.patpat.ui.join

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.simply407.patpat.R
import com.simply407.patpat.databinding.CommonMbtiBinding
import com.simply407.patpat.databinding.FragmentJoinMbtiFourthBinding

class JoinMbtiFourthFragment : Fragment() {

    private lateinit var joinInfoActivity: JoinInfoActivity
    private lateinit var binding: FragmentJoinMbtiFourthBinding
    private lateinit var commonMbtiBinding: CommonMbtiBinding
    private lateinit var firstMbti: String
    private lateinit var secondMbti: String
    private lateinit var thirdMbti: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        joinInfoActivity = activity as JoinInfoActivity
        binding = FragmentJoinMbtiFourthBinding.inflate(layoutInflater, container, false)
        commonMbtiBinding = CommonMbtiBinding.bind(binding.includeJoinMbtiFourth.root)
        firstMbti = arguments?.getString("firstMbti") ?: ""
        secondMbti = arguments?.getString("secondMbti") ?: ""
        thirdMbti = arguments?.getString("thirdMbti") ?: ""

        initUi()
        clickMbti()
        back()
        skip()

        return binding.root
    }

    private fun initUi() {
        binding.includeJoinMbtiFourth.editTextFirstJoinMbti.setText(firstMbti)
        binding.includeJoinMbtiFourth.editTextSecondJoinMbti.setText(secondMbti)
        binding.includeJoinMbtiFourth.editTextThirdJoinMbti.setText(thirdMbti)

        commonMbtiBinding.textViewFirstMbti.text = "P"
        commonMbtiBinding.textViewFirstMbti.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
        commonMbtiBinding.textViewSecondMbti.text = "J"
        commonMbtiBinding.textViewSecondMbti.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
    }

    private fun clickMbti() {
        commonMbtiBinding.cardViewFirstMbti.setOnClickListener {
            joinInfoActivity.animationMbti(context, commonMbtiBinding.textViewFirstMbti, commonMbtiBinding.textViewSecondMbti) {

                val bundle = Bundle()
                bundle.putString("firstMbti", firstMbti)
                bundle.putString("secondMbti", secondMbti)
                bundle.putString("thirdMbti", thirdMbti)
                bundle.putString("fourthMbti", "P")

                joinInfoActivity.addFragment(JoinInfoActivity.JOIN_MBTI_COMPLETE_FRAGMENT, true, bundle)
            }
        }

        commonMbtiBinding.cardViewSecondMbti.setOnClickListener {
            joinInfoActivity.animationMbti(context, commonMbtiBinding.textViewFirstMbti, commonMbtiBinding.textViewSecondMbti) {

                val bundle = Bundle()
                bundle.putString("firstMbti", firstMbti)
                bundle.putString("secondMbti", secondMbti)
                bundle.putString("thirdMbti", thirdMbti)
                bundle.putString("fourthMbti", "J")

                joinInfoActivity.addFragment(JoinInfoActivity.JOIN_MBTI_COMPLETE_FRAGMENT, true, bundle)
            }
        }
    }

    private fun back() {
        commonMbtiBinding.textViewBackJoinMbti.setOnClickListener {
            joinInfoActivity.removeFragment(JoinInfoActivity.JOIN_MBTI_FOURTH_FRAGMENT)
        }
    }

    private fun skip() {
        commonMbtiBinding.textViewSkipJoinMbti.setOnClickListener {
            joinInfoActivity.moveToMain()
        }
    }

}