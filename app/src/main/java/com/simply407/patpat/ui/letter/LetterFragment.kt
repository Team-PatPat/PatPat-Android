package com.simply407.patpat.ui.letter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.simply407.patpat.R
import com.simply407.patpat.data.model.SharedPreferencesManager
import com.simply407.patpat.databinding.FragmentLetter2Binding
import com.simply407.patpat.ui.main.MainActivity


class LetterFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentLetter2Binding

    private var currentPageIndex: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentLetter2Binding.inflate(layoutInflater)

        currentPageIndex = arguments?.getInt("currentPageIndex") ?: 0

        initUi(currentPageIndex)

        return binding.root
    }

    private fun initUi(index: Int) {
        binding.run {

            val subtitles = listOf(
                "복남이의 편지",
                "닥터 냉철한의 편지",
                "곽두팔의 편지",
                "코코의 편지"
            )

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

            materialToolbarLetter.run {
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.LETTER_FRAGMENT)
                }

                subtitle = subtitles.getOrNull(index) ?: "기본 제목"
            }

            // 사용자 이름 설정
            val userName = SharedPreferencesManager.getUserName()
            val formattedString = getString(R.string.user_name_letter, userName)
            textViewUserNameLetter.text = formattedString

            // 상담사 별 편지지 색상 설정
            val color = ContextCompat.getColor(requireContext(), letterColors.getOrNull(index) ?: R.color.letter_yellow)
            cardViewLetter.setCardBackgroundColor(color)

            // 상담사 별 편지지 줄 색상 설정
            editTextContentsLetter.setLineColor(
                lineColors.getOrNull(index) ?: R.color.line_yellow
            )

            // 상담사 별 From 설정
            textViewFromLetter.text = textFromList.getOrNull(index) ?: "From. 기본 이름"

            if (index == 0) {
                val randomImage = boknamImages.random()
                imageViewLetter.setImageResource(randomImage)
                imageViewLetter.visibility = View.VISIBLE
                textViewFooterLetter.visibility = View.GONE
            } else {
                imageViewLetter.visibility = View.GONE
                textViewFooterLetter.visibility = View.VISIBLE

                // 폰트 설정 (인덱스에 따라 다르게 설정)
                val typeface = when (index) {
                    1 -> ResourcesCompat.getFont(requireContext(), R.font.nanum_glad)
                    2 -> ResourcesCompat.getFont(requireContext(), R.font.nanum_gang)
                    3 -> ResourcesCompat.getFont(requireContext(), R.font.nanum_fly)
                    else -> textViewFooterLetter.typeface
                }
                textViewFooterLetter.typeface = typeface

                // 폰트 크기 설정 (인덱스에 따라 다르게 설정)
                val fontSize = when (index) {
                    3 -> 28f
                    else -> 22f
                }
                textViewFooterLetter.textSize = fontSize
            }

        }
    }

}