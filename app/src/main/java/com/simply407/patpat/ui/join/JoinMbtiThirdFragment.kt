package com.simply407.patpat.ui.join

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.simply407.patpat.R
import com.simply407.patpat.data.model.NewUserInfo
import com.simply407.patpat.data.model.SharedPreferencesManager
import com.simply407.patpat.databinding.CommonMbtiBinding
import com.simply407.patpat.databinding.FragmentJoinMbtiThirdBinding


class JoinMbtiThirdFragment : Fragment() {

    private lateinit var joinInfoActivity: JoinInfoActivity
    private lateinit var binding: FragmentJoinMbtiThirdBinding
    private lateinit var commonMbtiBinding: CommonMbtiBinding
    private lateinit var firstMbti: String
    private lateinit var secondMbti: String

    private lateinit var viewModel: JoinViewModel

    val TAG = "JoinMbtiThirdFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        joinInfoActivity = activity as JoinInfoActivity
        binding = FragmentJoinMbtiThirdBinding.inflate(layoutInflater, container, false)
        commonMbtiBinding = CommonMbtiBinding.bind(binding.includeJoinMbtiThird.root)
        firstMbti = arguments?.getString("firstMbti") ?: ""
        secondMbti = arguments?.getString("secondMbti") ?: ""

        viewModel = ViewModelProvider(requireActivity())[JoinViewModel::class.java]

        initUi()
        clickMbti()
        back()
        skip()
        observerData()

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
            val userName = joinInfoActivity.observerUserName()

            val newUserInfo = NewUserInfo(userName, "")
            Log.d(TAG, "newUserInfo: $newUserInfo")

            SharedPreferencesManager.getUserAccessToken()?.let { it1 ->
                viewModel.putUserInfo(it1, newUserInfo)
            }

        }
    }

    private fun observerData() {
        viewModel.userInfoResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess { userInfoResponse ->
                Log.d(TAG, "유저 정보 업데이트 성공: $userInfoResponse")
                joinInfoActivity.moveToMain()
            }
            result.onFailure { error ->
                Log.d(TAG, "유저 정보 업데이트 실패: $error")
            }
        }
    }

}