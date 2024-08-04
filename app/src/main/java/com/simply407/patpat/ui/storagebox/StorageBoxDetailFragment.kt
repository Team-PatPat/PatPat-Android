package com.simply407.patpat.ui.storagebox

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.simply407.patpat.R
import com.simply407.patpat.data.model.CreateLetterResponse
import com.simply407.patpat.data.model.SharedPreferencesManager
import com.simply407.patpat.databinding.FragmentStorageBoxDetailBinding
import com.simply407.patpat.ui.main.MainActivity


class StorageBoxDetailFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentStorageBoxDetailBinding

    private var counselorIndex: Int? = null
    private var letter: CreateLetterResponse? = null

    val TAG = "StorageBoxDetailFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentStorageBoxDetailBinding.inflate(layoutInflater, container, false)

        arguments?.let {
            counselorIndex = it.getInt("counselorIndex")
            letter = it.getParcelable("letter")
        }

        initUi(counselorIndex!!, letter!!)

        return binding.root
    }

    private fun initUi(index: Int, letterInfo: CreateLetterResponse) {
        binding.run {

            mainActivity.hideBottomNavigation()

            materialToolbarStorageBoxDetail.setNavigationOnClickListener {
                mainActivity.removeFragment(MainActivity.STORAGE_BOX_DETAIL_FRAGMENT)
            }

            val letterColors = listOf(
                R.color.letter_yellow,
                R.color.letter_blue,
                R.color.letter_red,
                R.color.letter_green
            )

            val lineColors = listOf(
                R.color.line_yellow,
                R.color.line_blue,
                R.color.line_red,
                R.color.line_green
            )

            val textPsList = listOf(
                R.string.letter_ps_boknam,
                R.string.letter_ps_doctor,
                R.string.letter_ps_kwak,
                R.string.letter_ps_coco
            )

            val textFromList = listOf(
                "From. 최고의 친구 복남이가",
                "From. 자네를 응원하는 닥터 냉철한",
                "From. 언제나 유쾌한 곽.두.팔",
                "From. 코코"
            )

            val boknamImages = listOf(
                R.drawable.img_boknam_one,
                R.drawable.img_boknam_two,
                R.drawable.img_boknam_three,
                R.drawable.img_boknam_four,
                R.drawable.img_boknam_five,
                R.drawable.img_boknam_six,
                R.drawable.img_boknam_seven,
                R.drawable.img_boknam_eight,
                R.drawable.img_boknam_nine,
                R.drawable.img_boknam_ten
            )

            // 사용자 이름 설정
            val userName = SharedPreferencesManager.getUserName()
            val formattedString = getString(R.string.user_name_letter, userName)
            textViewUserNameStorageBoxDetail.text = formattedString

            // 상담사 별 편지지 색상 설정
            val color = ContextCompat.getColor(requireContext(), letterColors.getOrNull(index) ?: R.color.letter_yellow)
            cardViewStorageBoxDetail.setCardBackgroundColor(color)

            // 상담사 별 편지지 줄 색상 설정
            editTextContentsStorageBoxDetail.setLineColor(
                lineColors.getOrNull(index) ?: R.color.line_yellow
            )

            // 상담사 별 PS 설정
            textViewPsStorageBoxDetail.text = getString(textPsList.getOrNull(index) ?: R.string.letter_ps_boknam)

            // 상담사 별 From 설정
            textViewFromStorageBoxDetail.text = textFromList.getOrNull(index) ?: "From. 기본 이름"

            if (index == 0) {
                val randomImage = boknamImages.random()
                imageViewStorageBoxDetail.setImageResource(randomImage)
                imageViewStorageBoxDetail.visibility = View.VISIBLE
                textViewFooterStorageBoxDetail.visibility = View.GONE
            } else {
                imageViewStorageBoxDetail.visibility = View.GONE
                textViewFooterStorageBoxDetail.visibility = View.VISIBLE

                // footer 설정
                textViewFooterStorageBoxDetail.text = letterInfo.footer

                // 폰트 설정 (인덱스에 따라 다르게 설정)
                val typeface = when (index) {
                    1 -> ResourcesCompat.getFont(requireContext(), R.font.nanum_glad)
                    2 -> ResourcesCompat.getFont(requireContext(), R.font.nanum_gang)
                    3 -> ResourcesCompat.getFont(requireContext(), R.font.nanum_fly)
                    else -> textViewFooterStorageBoxDetail.typeface
                }
                textViewFooterStorageBoxDetail.typeface = typeface

                // 폰트 크기 설정 (인덱스에 따라 다르게 설정)
                val fontSize = when (index) {
                    3 -> 28f
                    else -> 22f
                }
                textViewFooterStorageBoxDetail.textSize = fontSize
            }

            // 편지 내용 설정
            editTextContentsStorageBoxDetail.setText(letterInfo.content)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainActivity.showBottomNavigation()
    }

}