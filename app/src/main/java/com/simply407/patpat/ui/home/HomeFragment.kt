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
import com.simply407.patpat.data.model.MBTIRepository
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
        clickCounselor()

        return binding.root
    }

    private fun observeData() {
        loginViewModel.userInfoResult.observe(viewLifecycleOwner, Observer { result ->
            result.onSuccess { userInfoResponse ->
                // 유저 정보 성공 처리
                Log.d(TAG, "유저 정보 성공: $userInfoResponse")
                binding.textViewNameHome.text = userInfoResponse.name + "님,\n누구와 대화해 볼까요?"
                SharedPreferencesManager.setUserName(userInfoResponse.name)

                counselorRecommendation(userInfoResponse.mbti)
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
        }
    }

    private fun clickCounselor() {
        binding.run {
            // 복남이 선택
            includeBoknamHome.root.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("counselorsIndex", 0)

                mainActivity.addFragment(MainActivity.HOME_DETAIL_FRAGMENT, true, bundle)
            }

            // 닥터 선택
            includeDoctorHome.root.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("counselorsIndex", 1)

                mainActivity.addFragment(MainActivity.HOME_DETAIL_FRAGMENT, true, bundle)
            }

            // 곽두팔 선택
            includeKwakHome.root.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("counselorsIndex", 2)

                mainActivity.addFragment(MainActivity.HOME_DETAIL_FRAGMENT, true, bundle)
            }

            // 코코 선택
            includeCocoHome.root.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("counselorsIndex", 3)

                mainActivity.addFragment(MainActivity.HOME_DETAIL_FRAGMENT, true, bundle)
            }
        }
    }

    // 유저의 mbti 를 토대로 상담사 추천
    private fun counselorRecommendation(userMbti: String) {
        // 사용자의 MBTI와 일치하는 상담사 찾기
        val recommendedCounselor = MBTIRepository.CounselorMbtiDataList.find { counselorInfo ->
            counselorInfo.mbtiList.contains(userMbti)
        }

        Log.d(TAG, "recommendedCounselor: $recommendedCounselor")
        if (recommendedCounselor != null) {
            Log.d(TAG, "추천 상담사: ${recommendedCounselor.name}")
            SharedPreferencesManager.setCounselorRecommendation(recommendedCounselor.name)
            // UI 업데이트 등 필요한 작업 수행
            when (recommendedCounselor.name) {
                "복남이" -> {
                    binding.includeBoknamHome.imageViewStateCounselor.setImageResource(R.drawable.ic_thumbs_up)
                    binding.includeBoknamHome.textViewStateCounselor.text = "MBTI 추천"
                    binding.includeBoknamHome.linearLayoutStateCounselor.visibility = View.VISIBLE

                    // 나머지는 안보이게 처리
                    binding.includeDoctorHome.linearLayoutStateCounselor.visibility = View.GONE
                    binding.includeKwakHome.linearLayoutStateCounselor.visibility = View.GONE
                    binding.includeCocoHome.linearLayoutStateCounselor.visibility = View.GONE
                }
                "닥터 냉철한" -> {
                    binding.includeDoctorHome.imageViewStateCounselor.setImageResource(R.drawable.ic_thumbs_up)
                    binding.includeDoctorHome.textViewStateCounselor.text = "MBTI 추천"
                    binding.includeDoctorHome.linearLayoutStateCounselor.visibility = View.VISIBLE

                    // 나머지는 안보이게 처리
                    binding.includeBoknamHome.linearLayoutStateCounselor.visibility = View.GONE
                    binding.includeKwakHome.linearLayoutStateCounselor.visibility = View.GONE
                    binding.includeCocoHome.linearLayoutStateCounselor.visibility = View.GONE
                }
                "곽두팔" -> {
                    binding.includeKwakHome.imageViewStateCounselor.setImageResource(R.drawable.ic_thumbs_up)
                    binding.includeKwakHome.textViewStateCounselor.text = "MBTI 추천"
                    binding.includeKwakHome.linearLayoutStateCounselor.visibility = View.VISIBLE

                    // 나머지는 안보이게 처리
                    binding.includeBoknamHome.linearLayoutStateCounselor.visibility = View.GONE
                    binding.includeDoctorHome.linearLayoutStateCounselor.visibility = View.GONE
                    binding.includeCocoHome.linearLayoutStateCounselor.visibility = View.GONE
                }
                "코코" -> {
                    binding.includeCocoHome.imageViewStateCounselor.setImageResource(R.drawable.ic_thumbs_up)
                    binding.includeCocoHome.textViewStateCounselor.text = "MBTI 추천"
                    binding.includeCocoHome.linearLayoutStateCounselor.visibility = View.VISIBLE

                    // 나머지는 안보이게 처리
                    binding.includeBoknamHome.linearLayoutStateCounselor.visibility = View.GONE
                    binding.includeDoctorHome.linearLayoutStateCounselor.visibility = View.GONE
                    binding.includeKwakHome.linearLayoutStateCounselor.visibility = View.GONE
                }
            }

        } else {
            Log.d(TAG, "추천할 상담사가 없습니다.")
            binding.includeBoknamHome.linearLayoutStateCounselor.visibility = View.GONE
            binding.includeDoctorHome.linearLayoutStateCounselor.visibility = View.GONE
            binding.includeKwakHome.linearLayoutStateCounselor.visibility = View.GONE
            binding.includeCocoHome.linearLayoutStateCounselor.visibility = View.GONE
        }
    }

}