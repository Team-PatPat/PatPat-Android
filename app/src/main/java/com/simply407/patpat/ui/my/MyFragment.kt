package com.simply407.patpat.ui.my

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.simply407.patpat.R
import com.simply407.patpat.databinding.DialogChangeNicknameBinding
import com.simply407.patpat.databinding.FragmentMyBinding
import com.simply407.patpat.databinding.ItemSaveLetterBinding
import com.simply407.patpat.ui.login.LoginViewModel
import com.simply407.patpat.ui.main.MainActivity


class MyFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentMyBinding

    private lateinit var loginViewModel: LoginViewModel

    private var userName = ""

    val TAG = "MyFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentMyBinding.inflate(layoutInflater)

        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]

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
            }
            result.onFailure { error ->
                // 로그인 실패 처리
                Log.d(TAG, "유저 정보 실패: $error")
            }
        }
    }

    private fun linearLayoutFunction() {
        binding.linearLayoutChangeNicknameMy.setOnClickListener {
            changeNickname()
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
            dialog.dismiss()
        }

        dialog.show()
    }

}