package com.simply407.patpat.ui.letter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.simply407.patpat.R
import com.simply407.patpat.data.model.CreateLetterResponse
import com.simply407.patpat.data.model.LikeLetterRequest
import com.simply407.patpat.data.model.SharedPreferencesManager
import com.simply407.patpat.databinding.FragmentLetter2Binding
import com.simply407.patpat.databinding.ItemLoadingLetterBinding
import com.simply407.patpat.ui.main.MainActivity


class LetterFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentLetter2Binding

    private lateinit var letterViewModel: LetterViewModel

    private var currentPageIndex: Int = 0
    private var currentLetterId: String = ""

    private lateinit var loadingDialog: AlertDialog

    val TAG = "LetterFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentLetter2Binding.inflate(layoutInflater)

        currentPageIndex = arguments?.getInt("currentPageIndex") ?: 0

        letterViewModel = ViewModelProvider(requireActivity())[LetterViewModel::class.java]

        initUi(currentPageIndex)
        observeData()
        likeLetter()

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

            materialToolbarLetter.run {
                setNavigationOnClickListener {
                    mainActivity.removeAllBackStack()
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

            // 상담사 별 PS 설정
            textViewPsLetter.text = getString(textPsList.getOrNull(index) ?: R.string.letter_ps_boknam)

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

    private fun observeData() {
        letterViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                showLoading()
            } else {
                hideLoading()
            }
        }

        letterViewModel.createLetterResult.observe(viewLifecycleOwner) { result ->
            result.onSuccess { createLetterResponse ->
                mainActivity.logLongMessage(TAG, "createLetter 성공: $createLetterResponse")
                currentLetterId = createLetterResponse.id
                settingUi(createLetterResponse)
            }
            result.onFailure { error ->
                mainActivity.logLongMessage(TAG, "createLetter 실패: $error")
                mainActivity.removeFragment(MainActivity.LETTER_FRAGMENT)
                Snackbar.make(binding.root, "편지 생성에 실패했습니다.", Snackbar.LENGTH_SHORT).show()
            }
        }

        letterViewModel.likeLetterResult.observe(viewLifecycleOwner) { result ->
            val include = binding.includeLetterFunction

            result.onSuccess { createLetterResponse ->
                mainActivity.logLongMessage(TAG, "likeLetter 성공: $createLetterResponse")

                include.imageViewLikeLetterFunction.isSelected = !include.imageViewLikeLetterFunction.isSelected
                if (include.imageViewLikeLetterFunction.isSelected) {
                    include.imageViewLikeLetterFunction.setImageResource(R.drawable.ic_letter_full_star)
                } else {
                    include.imageViewLikeLetterFunction.setImageResource(R.drawable.ic_letter_star)
                }
            }
            result.onFailure { error ->
                mainActivity.logLongMessage(TAG, "likeLetter 실패: $error")

                if (include.imageViewLikeLetterFunction.isSelected) {
                    include.imageViewLikeLetterFunction.setImageResource(R.drawable.ic_letter_star)
                } else {
                    include.imageViewLikeLetterFunction.setImageResource(R.drawable.ic_letter_full_star)
                }
            }
        }

    }

    private fun settingUi(createLetterResponse : CreateLetterResponse) {
        binding.run {
            editTextContentsLetter.setText(createLetterResponse.content)

            // footer 설정
            if (createLetterResponse.footer != null) {
                textViewFooterLetter.text = createLetterResponse.footer
            } else {
                textViewFooterLetter.text = ""
            }
        }
    }

    private fun showLoading() {
        if (!::loadingDialog.isInitialized) {
            createLoadingDialog()
        }
        loadingDialog.show()
        binding.linearLayoutMainLetter.visibility = View.GONE
    }

    private fun hideLoading() {
        if (::loadingDialog.isInitialized && loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
        binding.linearLayoutMainLetter.visibility = View.VISIBLE
    }

    private fun createLoadingDialog() {
        val itemLoadingLetterBinding = ItemLoadingLetterBinding.inflate(layoutInflater)
        val builder = MaterialAlertDialogBuilder(mainActivity)
        builder.setView(itemLoadingLetterBinding.root)
        builder.setCancelable(false)

        loadingDialog = builder.create()
    }

    private fun likeLetter() {
        val include = binding.includeLetterFunction
        include.imageViewLikeLetterFunction.setOnClickListener {
            val isLiked = !include.imageViewLikeLetterFunction.isSelected
            val likeLetterRequest = LikeLetterRequest(isLiked)
            letterViewModel.likeLetter(SharedPreferencesManager.getUserAccessToken()!!, currentLetterId, likeLetterRequest)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        if (::loadingDialog.isInitialized && loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }

}