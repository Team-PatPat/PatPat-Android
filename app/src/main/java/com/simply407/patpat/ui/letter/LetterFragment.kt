package com.simply407.patpat.ui.letter

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.simply407.patpat.R
import com.simply407.patpat.data.letter
import com.simply407.patpat.data.model.SharedPreferencesManager
import com.simply407.patpat.databinding.FragmentLetterBinding
import kotlin.random.Random

class LetterFragment:Fragment() {

    companion object{
        private const val ROOM1="복남이"
        private const val ROOM2="닥터 냉철한"
        private const val ROOM3="곽두팔"
        private const val ROOM4="코코"
    }

    private lateinit var binding : FragmentLetterBinding
    private lateinit var viewModel: LetterViewModel
    private lateinit var themeValues : letter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.d("Why So Serious","ㄴㄴㄴㄴㄴ")
        binding = FragmentLetterBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity())[LetterViewModel::class.java]

        themeValues = viewModel.get_letter_info()
        if(themeValues.counselor== ROOM1){
            binding.letterSendImage.visibility=View.VISIBLE
            binding.letterSendComment.visibility=View.GONE
        }else{
            binding.letterSendImage.visibility=View.GONE
            binding.letterSendComment.visibility=View.VISIBLE
        }

        makeTheme(themeValues.counselor)

        return binding.root
    }

    private fun makeTheme(counselor: String) {

        // 사용자 이름
        binding.letterSendUser.text = SharedPreferencesManager.getUserName() + "에게"

        if(counselor==ROOM1){
            Log.d("sfsfjslf",themeValues.commentImage.toString())
            binding.letterSendImage.setBackgroundResource(randomPicture())
        }else{
            binding.letterSendComment.text=themeValues.commentString
        }

        binding.letterSendContent.text=themeValues.content
        binding.letterSendComment.text=themeValues.commentString

        val drawable_frame=ContextCompat.getDrawable(requireContext(),R.drawable.letter_contentsframe)
        val drawable_line=ContextCompat.getDrawable(requireContext(),R.drawable.letter_lineframe)
        binding.letterCardFrame.background=drawable_frame
        binding.letterLineFrame.background=drawable_line


        when(counselor){
            ROOM1->{
                binding.letterSendFrom.text= requireContext().getString(R.string.letterFrom1)
                binding.letterSendPs.text= requireContext().getString(R.string.letterPs1)
                if (drawable_frame != null && drawable_line!=null) {
                    DrawableCompat.setTint(drawable_frame,ContextCompat.getColor(requireContext(),R.color.sub_yellow_light))
                    DrawableCompat.setTint(drawable_line,ContextCompat.getColor(requireContext(),R.color.sub_yellow))
                }
            }
            ROOM2->{
                binding.letterSendFrom.text= requireContext().getString(R.string.letterFrom2)
                binding.letterSendPs.text= requireContext().getString(R.string.letterPs2)
                if (drawable_frame != null && drawable_line!=null) {
                    DrawableCompat.setTint(drawable_frame,ContextCompat.getColor(requireContext(),R.color.sub_blue_light))
                    DrawableCompat.setTint(drawable_line,ContextCompat.getColor(requireContext(),R.color.sub_blue))
                }
                val typeface = ResourcesCompat.getFont(requireContext(), R.font.nanum_glad)
                binding.letterSendComment.typeface = typeface
            }

            ROOM3->{
                binding.letterSendFrom.text= requireContext().getString(R.string.letterFrom3)
                binding.letterSendPs.text= requireContext().getString(R.string.letterPs3)
                if (drawable_frame != null && drawable_line!=null) {
                    DrawableCompat.setTint(drawable_frame,ContextCompat.getColor(requireContext(),R.color.sub_red_light))
                    DrawableCompat.setTint(drawable_line,ContextCompat.getColor(requireContext(),R.color.sub_red))
                }
                val typeface = ResourcesCompat.getFont(requireContext(), R.font.nanum_gang)
                binding.letterSendComment.typeface = typeface
            }

            else->{
                binding.letterSendFrom.text= requireContext().getString(R.string.letterFrom4)
                binding.letterSendPs.text= requireContext().getString(R.string.letterPs4)
                if (drawable_frame != null && drawable_line!=null) {
                    DrawableCompat.setTint(drawable_frame,ContextCompat.getColor(requireContext(),R.color.sub_green_light))
                    DrawableCompat.setTint(drawable_line,ContextCompat.getColor(requireContext(),R.color.sub_green))
                }
                val typeface = ResourcesCompat.getFont(requireContext(), R.font.nanum_fly)
                binding.letterSendComment.typeface = typeface
            }
        }

    }

    private fun randomPicture() : Int{
        var value= Random.nextInt(1,11)

        when(value){
            1->value=R.drawable.boknam1
            2->value=R.drawable.boknam2
            3->value=R.drawable.boknam3
            4->value=R.drawable.boknam4
            5->value=R.drawable.boknam5
            6->value=R.drawable.boknam6
            7->value=R.drawable.boknam7
            8->value=R.drawable.boknam8
            9->value=R.drawable.boknam9
            else->R.drawable.boknam10
        }
        return value


    }
}