package com.simply407.patpat.ui.join

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.simply407.patpat.data.model.NewUserInfo
import com.simply407.patpat.data.model.SharedPreferencesManager
import com.simply407.patpat.databinding.CommonMbtiBinding
import com.simply407.patpat.databinding.FragmentJoinMbtiFirstBinding

class JoinMbtiFirstFragment : Fragment() {

    private lateinit var joinInfoActivity: JoinInfoActivity
    private lateinit var binding: FragmentJoinMbtiFirstBinding
    private lateinit var commonMbtiBinding: CommonMbtiBinding

    private lateinit var viewModel: JoinViewModel

    val TAG = "JoinMbtiFirstFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        joinInfoActivity = activity as JoinInfoActivity
        binding = FragmentJoinMbtiFirstBinding.inflate(layoutInflater, container, false)
        commonMbtiBinding = CommonMbtiBinding.bind(binding.includeJoinMbtiFirst.root)

        viewModel = ViewModelProvider(requireActivity())[JoinViewModel::class.java]

        clickMbti()
        back()
        skip()
        observerData()

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