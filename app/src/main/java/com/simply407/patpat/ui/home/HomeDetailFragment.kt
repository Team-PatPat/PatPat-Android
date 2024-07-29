package com.simply407.patpat.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.simply407.patpat.R
import com.simply407.patpat.data.model.GetCounselorResponse
import com.simply407.patpat.data.model.SharedPreferencesManager
import com.simply407.patpat.databinding.FragmentHomeDetailBinding
import com.simply407.patpat.ui.chat.ChattingActivity
import com.simply407.patpat.ui.main.MainActivity


class HomeDetailFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentHomeDetailBinding

    private lateinit var homeViewModel: HomeViewModel

    private var counselorsIndex: Int = 0
    private var currentPageIndex: Int = 0

    private lateinit var counselorDataList: List<GetCounselorResponse>

    val TAG = "HomeDetailFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentHomeDetailBinding.inflate(layoutInflater)

        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        SharedPreferencesManager.getUserAccessToken()?.let {
            homeViewModel.getCounselors(it)
        }

        counselorsIndex = arguments?.getInt("counselorsIndex") ?: 0

        initUi()
        observeData()
        moveToChatting()

        return binding.root
    }

    private fun initUi() {
        binding.run {
            viewPagerHomeDetail.run {
                val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
                val pagerWidth = resources.getDimensionPixelOffset(R.dimen.offset)
                val screenWidth = resources.displayMetrics.widthPixels
                val offsetPx = screenWidth - pageMarginPx - pagerWidth

                binding.viewPagerHomeDetail.setPageTransformer { page, position ->
                    page.translationX = position * -offsetPx
                }

                adapter = HomeDetailAdapter()
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                offscreenPageLimit = 2

                // 무한 스크롤을 위해 시작 위치를 설정합니다.
                val startPosition = Int.MAX_VALUE / 2 - (Int.MAX_VALUE / 2 % 4) + counselorsIndex
                setCurrentItem(startPosition, false)

                currentPageIndex = startPosition % 4
                registerOnPageChangeCallback()
            }
        }
    }

    private fun registerOnPageChangeCallback() {
        binding.viewPagerHomeDetail.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                currentPageIndex = position % 4
            }
        })
    }

    private fun observeData() {
        homeViewModel.counselorList.observe(viewLifecycleOwner) { result ->
            result.onSuccess { counselorList ->
                Log.d(TAG, "상담사 정보 성공: $counselorList")
                counselorDataList = counselorList
            }
            result.onFailure { error ->
                Log.d(TAG, "상담사 정보 실패: $error")
            }

        }
    }

    private fun moveToChatting() {
        binding.buttonTalkHomeDetail.setOnClickListener {
            mainActivity.addFragment(MainActivity.CHATTING_FRAGMENT, true, null)
        }
    }

}