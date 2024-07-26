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
import com.simply407.patpat.databinding.FragmentJoinMbtiCompleteBinding
import com.simply407.patpat.databinding.FragmentJoinMbtiFourthBinding


class JoinMbtiCompleteFragment : Fragment() {

    private lateinit var joinInfoActivity: JoinInfoActivity
    private lateinit var binding: FragmentJoinMbtiCompleteBinding

    private lateinit var firstMbti: String
    private lateinit var secondMbti: String
    private lateinit var thirdMbti: String
    private lateinit var fourthMbti: String

    private lateinit var viewModel: JoinViewModel

    val TAG = "JoinMbtiCompleteFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        joinInfoActivity = activity as JoinInfoActivity
        binding = FragmentJoinMbtiCompleteBinding.inflate(layoutInflater, container, false)

        firstMbti = arguments?.getString("firstMbti") ?: ""
        secondMbti = arguments?.getString("secondMbti") ?: ""
        thirdMbti = arguments?.getString("thirdMbti") ?: ""
        fourthMbti = arguments?.getString("fourthMbti") ?: ""

        viewModel = ViewModelProvider(requireActivity())[JoinViewModel::class.java]

        initUi()
        observerData()
        back()
        clickButton()

        return binding.root
    }

    private fun initUi() {
        binding.editTextFirstJoinMbtiComplete.setText(firstMbti)
        binding.editTextSecondJoinMbtiComplete.setText(secondMbti)
        binding.editTextThirdJoinMbtiComplete.setText(thirdMbti)
        binding.editTextFourthJoinMbtiComplete.setText(fourthMbti)

        binding.textViewFirstMbtiComplete.text = firstMbti
        binding.textViewSecondMbtiComplete.text = secondMbti
        binding.textViewThirdMbtiComplete.text = thirdMbti
        binding.textViewFourthMbtiComplete.text = fourthMbti

        checkMbtiColor()
    }

    private fun checkMbtiColor() {
        when (firstMbti) {
            "I" -> binding.textViewFirstMbtiComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
            "E" -> binding.textViewFirstMbtiComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }

        when(secondMbti) {
            "S" -> binding.textViewSecondMbtiComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
            "N" -> binding.textViewSecondMbtiComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
        }

        when(thirdMbti) {
            "F" -> binding.textViewThirdMbtiComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
            "T" -> binding.textViewThirdMbtiComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
        }

        when(fourthMbti) {
            "P" -> binding.textViewFourthMbtiComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
            "J" -> binding.textViewFourthMbtiComplete.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
        }

    }

    private fun observerData() {
        viewModel.nickName.observe(viewLifecycleOwner) { name ->
            binding.textViewNameJoinMbtiComplete.text = "${name}님께\n맞는 친구를 추천해 드릴게요!"
        }

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

    private fun back() {
        binding.textViewBackJoinMbtiComplete.setOnClickListener {
            joinInfoActivity.removeFragment(JoinInfoActivity.JOIN_MBTI_COMPLETE_FRAGMENT)
        }
    }

    private fun clickButton() {
        binding.buttonStartJoinMbtiComplete.setOnClickListener {

            val newUserInfo = NewUserInfo(viewModel.nickName.value!!, firstMbti + secondMbti + thirdMbti + fourthMbti)
            Log.d(TAG, "newUserInfo: $newUserInfo")

            SharedPreferencesManager.getUserAccessToken()?.let { it1 ->
                viewModel.putUserInfo(it1, newUserInfo)
            }

        }
    }

}