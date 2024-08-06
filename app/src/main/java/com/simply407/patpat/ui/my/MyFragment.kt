package com.simply407.patpat.ui.my

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.simply407.patpat.R
import com.simply407.patpat.data.model.NewUserInfo
import com.simply407.patpat.data.model.SharedPreferencesManager
import com.simply407.patpat.databinding.DialogChangeMbtiBinding
import com.simply407.patpat.databinding.DialogChangeNicknameBinding
import com.simply407.patpat.databinding.FragmentMyBinding
import com.simply407.patpat.databinding.ItemSaveLetterBinding
import com.simply407.patpat.ui.join.JoinViewModel
import com.simply407.patpat.ui.login.LoginViewModel
import com.simply407.patpat.ui.main.MainActivity


class MyFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentMyBinding

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var joinViewModel: JoinViewModel

    private var userName = ""
    private var userMbti = ""

    val TAG = "MyFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentMyBinding.inflate(layoutInflater)

        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        joinViewModel = ViewModelProvider(this)[JoinViewModel::class.java]

        observeData()
        linearLayoutFunction()

        return binding.root
    }

    private fun observeData() {
        loginViewModel.userInfoResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess { userInfoResponse ->
                // 유저 정보 성공 처리
                Log.d(TAG, "유저 정보 성공: $userInfoResponse")

                // 프로필 설정
                Glide.with(this)
                    .load(userInfoResponse.avatarUrl)
                    .error(R.drawable.ic_default_profile) // 로드 실패 시 표시할 이미지
                    .into(binding.imageViewUserProfileMy)

                userName = userInfoResponse.name
                userMbti = userInfoResponse.mbti
            }
            result.onFailure { error ->
                // 로그인 실패 처리
                Log.d(TAG, "유저 정보 실패: $error")
            }
        }

        joinViewModel.userInfoResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess { userInfoResponse ->
                Log.d(TAG, "유저 정보 변경 성공: $userInfoResponse")
                loginViewModel.getUserInfo(SharedPreferencesManager.getUserAccessToken()!!)
            }
            result.onFailure { error ->
                Log.d(TAG, "유저 정보 변경 실패: $error")
            }
        }
    }

    private fun linearLayoutFunction() {
        binding.linearLayoutChangeNicknameMy.setOnClickListener {
            changeNickname()
        }
        binding.linearLayoutChangeMbtiMy.setOnClickListener {
            changeMbti()
        }
    }

    // 닉네임 변경
    private fun changeNickname() {
        val dialogChangeNicknameBinding = DialogChangeNicknameBinding.inflate(layoutInflater)
        val builder = MaterialAlertDialogBuilder(mainActivity)
        builder.setView(dialogChangeNicknameBinding.root)
        val dialog = builder.create()

        dialogChangeNicknameBinding.textInputEditTextDialogChangeNickName.setText(userName)

        dialogChangeNicknameBinding.buttonCancelDialogChangeNickName.setOnClickListener {
            dialog.dismiss()
        }

        dialogChangeNicknameBinding.buttonChangeDialogChangeNickName.setOnClickListener {

            val newUserName = dialogChangeNicknameBinding.textInputEditTextDialogChangeNickName.text.toString()

            if (newUserName.isNotEmpty()) {
                val newUserInfo = NewUserInfo(newUserName, userMbti)
                joinViewModel.putUserInfo(SharedPreferencesManager.getUserAccessToken()!!, newUserInfo)

                dialog.dismiss()
            }

        }

        dialog.show()
    }

    // MBTI 변경
    private fun changeMbti() {
        val dialogChangeMbtiBinding = DialogChangeMbtiBinding.inflate(layoutInflater)
        val builder = MaterialAlertDialogBuilder(mainActivity)
        builder.setView(dialogChangeMbtiBinding.root)

        val dialog = builder.create()

        // CheckBoxes를 리스트에 추가
        val checkboxes = listOf(
            dialogChangeMbtiBinding.checkboxIstj,
            dialogChangeMbtiBinding.checkboxIsfj,
            dialogChangeMbtiBinding.checkboxInfj,
            dialogChangeMbtiBinding.checkboxIntj,
            dialogChangeMbtiBinding.checkboxIstp,
            dialogChangeMbtiBinding.checkboxIsfp,
            dialogChangeMbtiBinding.checkboxInfp,
            dialogChangeMbtiBinding.checkboxIntp,
            dialogChangeMbtiBinding.checkboxEstp,
            dialogChangeMbtiBinding.checkboxEsfp,
            dialogChangeMbtiBinding.checkboxEnfp,
            dialogChangeMbtiBinding.checkboxEntp,
            dialogChangeMbtiBinding.checkboxEstj,
            dialogChangeMbtiBinding.checkboxEsfj,
            dialogChangeMbtiBinding.checkboxEnfj,
            dialogChangeMbtiBinding.checkboxEntj
        )

        // 단일 선택 로직 구현
        for (checkbox in checkboxes) {
            checkbox.setOnClickListener {
                for (cb in checkboxes) {
                    if (cb != checkbox) {
                        cb.isChecked = false
                    }
                }
            }
        }

        dialogChangeMbtiBinding.buttonCancelDialogChangeMbti.setOnClickListener {
            dialog.dismiss()
        }

        dialogChangeMbtiBinding.buttonChangeDialogChangeMbti.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

}