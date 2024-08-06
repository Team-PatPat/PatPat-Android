package com.simply407.patpat.ui.my

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.simply407.patpat.R
import com.simply407.patpat.databinding.FragmentMyBinding
import com.simply407.patpat.ui.login.LoginViewModel
import com.simply407.patpat.ui.main.MainActivity


class MyFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentMyBinding

    private lateinit var loginViewModel: LoginViewModel

    val TAG = "MyFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentMyBinding.inflate(layoutInflater)

        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]

        observeData()

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
            }
            result.onFailure { error ->
                // 로그인 실패 처리
                Log.d(TAG, "유저 정보 실패: $error")
            }
        }
    }

}