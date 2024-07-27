package com.simply407.patpat.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.simply407.patpat.R
import com.simply407.patpat.databinding.FragmentHomeDetailBinding
import com.simply407.patpat.ui.main.MainActivity


class HomeDetailFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentHomeDetailBinding

    private var counselorsIndex: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentHomeDetailBinding.inflate(layoutInflater)

        counselorsIndex = arguments?.getInt("counselorsIndex") ?: 0

        initUi()

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
            }
        }
    }

}