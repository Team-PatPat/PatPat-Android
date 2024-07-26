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
import com.simply407.patpat.databinding.FragmentJoinMbtiSecondBinding


class JoinMbtiSecondFragment : Fragment() {

    private lateinit var joinInfoActivity: JoinInfoActivity
    private lateinit var binding: FragmentJoinMbtiSecondBinding
    private lateinit var commonMbtiBinding: CommonMbtiBinding
    private lateinit var firstMbti: String

    private lateinit var viewModel: JoinViewModel

    val TAG = "JoinMbtiSecondFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        joinInfoActivity = activity as JoinInfoActivity
        binding = FragmentJoinMbtiSecondBinding.inflate(layoutInflater, container, false)
        commonMbtiBinding = CommonMbtiBinding.bind(binding.includeJoinMbtiSecond.root)
        firstMbti = arguments?.getString("firstMbti") ?: ""

        viewModel = ViewModelProvider(requireActivity())[JoinViewModel::class.java]

        initUi()
        clickMbti()
        back()
        skip()
        observerData()

        return binding.root
    }

    private fun initUi() {
        binding.includeJoinMbtiSecond.editTextFirstJoinMbti.setText(firstMbti)

        commonMbtiBinding.textViewFirstMbti.text = "S"
        commonMbtiBinding.textViewFirstMbti.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
        commonMbtiBinding.textViewSecondMbti.text = "N"
        commonMbtiBinding.textViewSecondMbti.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
    }

    private fun clickMbti() {
        commonMbtiBinding.cardViewFirstMbti.setOnClickListener {
            joinInfoActivity.animationMbti(context, commonMbtiBinding.textViewFirstMbti, commonMbtiBinding.textViewSecondMbti) {

                val bundle = Bundle()
                bundle.putString("firstMbti", firstMbti)
                bundle.putString("secondMbti", "S")

                joinInfoActivity.addFragment(JoinInfoActivity.JOIN_MBTI_THIRD_FRAGMENT, true, bundle)
            }
        }

        commonMbtiBinding.cardViewSecondMbti.setOnClickListener {
            joinInfoActivity.animationMbti(context, commonMbtiBinding.textViewFirstMbti, commonMbtiBinding.textViewSecondMbti) {

                val bundle = Bundle()
                bundle.putString("firstMbti", firstMbti)
                bundle.putString("secondMbti", "N")

                joinInfoActivity.addFragment(JoinInfoActivity.JOIN_MBTI_THIRD_FRAGMENT, true, bundle)
            }
        }
    }

    private fun back() {
        commonMbtiBinding.textViewBackJoinMbti.setOnClickListener {
            joinInfoActivity.removeFragment(JoinInfoActivity.JOIN_MBTI_SECOND_FRAGMENT)
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