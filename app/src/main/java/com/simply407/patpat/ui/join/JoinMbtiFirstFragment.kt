package com.simply407.patpat.ui.join

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.simply407.patpat.databinding.CommonMbtiBinding
import com.simply407.patpat.databinding.FragmentJoinMbtiFirstBinding

class JoinMbtiFirstFragment : Fragment() {

    private lateinit var joinInfoActivity: JoinInfoActivity
    private lateinit var binding: FragmentJoinMbtiFirstBinding
    private lateinit var commonMbtiBinding: CommonMbtiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        joinInfoActivity = activity as JoinInfoActivity
        binding = FragmentJoinMbtiFirstBinding.inflate(layoutInflater, container, false)
        commonMbtiBinding = CommonMbtiBinding.bind(binding.includeJoinMbtiFirst.root)

        clickMbti()
        back()
        skip()

        return binding.root
    }

    private fun clickMbti() {
        commonMbtiBinding.cardViewFirstMbti.setOnClickListener {
            joinInfoActivity.animationMbti(context, commonMbtiBinding.textViewFirstMbti, commonMbtiBinding.textViewSecondMbti) {

                val bundle = Bundle()
                bundle.putString("firstMbti", "I")

                joinInfoActivity.addFragment(JoinInfoActivity.JOIN_MBTI_SECOND_FRAGMENT, true, bundle)
            }
        }

        commonMbtiBinding.cardViewSecondMbti.setOnClickListener {
            joinInfoActivity.animationMbti(context, commonMbtiBinding.textViewFirstMbti, commonMbtiBinding.textViewSecondMbti) {

                val bundle = Bundle()
                bundle.putString("firstMbti", "E")

                joinInfoActivity.addFragment(JoinInfoActivity.JOIN_MBTI_SECOND_FRAGMENT, true, bundle)
            }
        }
    }

    private fun back() {
        commonMbtiBinding.textViewBackJoinMbti.setOnClickListener {
            joinInfoActivity.removeFragment(JoinInfoActivity.JOIN_MBTI_FIRST_FRAGMENT)
        }
    }

    private fun skip() {
        commonMbtiBinding.textViewSkipJoinMbti.setOnClickListener {
            joinInfoActivity.moveToMain()
        }
    }

}