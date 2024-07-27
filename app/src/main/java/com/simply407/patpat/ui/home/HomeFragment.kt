package com.simply407.patpat.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.simply407.patpat.R
import com.simply407.patpat.data.model.SharedPreferencesManager
import com.simply407.patpat.databinding.FragmentHomeBinding
import com.simply407.patpat.databinding.ItemCounselorBinding
import com.simply407.patpat.ui.login.LoginViewModel
import com.simply407.patpat.ui.main.MainActivity

class HomeFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentHomeBinding

    private lateinit var loginViewModel: LoginViewModel

    val TAG = "HomeFragment1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        SharedPreferencesManager.getUserAccessToken()?.let { loginViewModel.getUserInfo(it) }

        observeData()
        initUi()

        return binding.root
    }

    private fun observeData() {
        loginViewModel.userInfoResult.observe(viewLifecycleOwner, Observer { result ->
            result.onSuccess { userInfoResponse ->
                // 유저 정보 성공 처리
                Log.d(TAG, "유저 정보 성공: $userInfoResponse")
                binding.textViewNameHome.text = userInfoResponse.name + "님,\n누구와 대화해 볼까요?"
            }
            result.onFailure { error ->
                // 로그인 실패 처리
                Log.d(TAG, "유저 정보 실패: $error")
            }
        })
    }

    private fun initUi() {
        binding.run {

            // 상담자 사진 설정
            val doctorCounselorBinding = ItemCounselorBinding.bind(binding.includeDoctorHome.root)
            doctorCounselorBinding.imageViewCounselor.setImageResource(R.drawable.ic_doctor)

            val kwakCounselorBinding = ItemCounselorBinding.bind(binding.includeKwakHome.root)
            kwakCounselorBinding.imageViewCounselor.setImageResource(R.drawable.img_kwak)

            val cocoCounselorBinding = ItemCounselorBinding.bind(binding.includeCocoHome.root)
            cocoCounselorBinding.imageViewCounselor.setImageResource(R.drawable.ic_coco)
        }
    }

}