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
    private lateinit var homeViewModel: HomeViewModel

    val TAG = "HomeFragment1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        SharedPreferencesManager.getUserAccessToken()?.let {
            loginViewModel.getUserInfo(it)
            homeViewModel.getCounselors(it)
        }

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

        homeViewModel.counselorList.observe(viewLifecycleOwner) { result ->

            Log.d(TAG, "homeViewModel.counselorList.observe 결과 : $result")
            result.onSuccess { counselorList ->
                // 상담 리스트 성공 처리
                Log.d(TAG, "상담사 정보 성공: $counselorList")
                counselorList.forEachIndexed { index, counselor ->

                    val name = counselor.name

                    when (index) {
                        0 -> {
                            binding.includeBoknamHome.textViewNameCounselor.text = name
                            binding.includeBoknamHome.textViewIntroductionCounselor.text = getString(R.string.bokname_introduction)
                        }
                        1 -> {
                            binding.includeDoctorHome.textViewNameCounselor.text = name
                            binding.includeDoctorHome.textViewIntroductionCounselor.text = getString(R.string.doctor_introduction)
                        }
                        2 -> {
                            binding.includeKwakHome.textViewNameCounselor.text = name
                            binding.includeKwakHome.textViewIntroductionCounselor.text = getString(R.string.kwak_introduction)
                        }
                        3 -> {
                            binding.includeCocoHome.textViewNameCounselor.text = name
                            binding.includeCocoHome.textViewIntroductionCounselor.text = getString(R.string.coco_introduction)
                        }
                    }
                }
            }

            result.onFailure { error ->
                // 상담 리스트 실패 처리
                Log.d(TAG, "상담사 정보 실패: $error")
            }
        }

    }

    private fun initUi() {
        binding.run {

            // 상담자 사진 설정
            val boknamCounselorBinding = ItemCounselorBinding.bind(binding.includeBoknamHome.root)
            boknamCounselorBinding.imageViewCounselor.setImageResource(R.drawable.img_boknam)

            val doctorCounselorBinding = ItemCounselorBinding.bind(binding.includeDoctorHome.root)
            doctorCounselorBinding.imageViewCounselor.setImageResource(R.drawable.ic_doctor)

            val kwakCounselorBinding = ItemCounselorBinding.bind(binding.includeKwakHome.root)
            kwakCounselorBinding.imageViewCounselor.setImageResource(R.drawable.img_kwak)

            val cocoCounselorBinding = ItemCounselorBinding.bind(binding.includeCocoHome.root)
            cocoCounselorBinding.imageViewCounselor.setImageResource(R.drawable.ic_coco)

            // 임시 화면 설정
            doctorCounselorBinding.imageViewStateCounselor.setImageResource(R.drawable.ic_thumbs_up)
            doctorCounselorBinding.textViewStateCounselor.text = "MBTI 추천"
            kwakCounselorBinding.linearLayoutStateCounselor.visibility = View.GONE
            cocoCounselorBinding.linearLayoutStateCounselor.visibility = View.GONE
        }
    }

}