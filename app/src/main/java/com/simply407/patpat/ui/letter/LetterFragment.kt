package com.simply407.patpat.ui.letter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.simply407.patpat.R
import com.simply407.patpat.databinding.FragmentLetter2Binding
import com.simply407.patpat.ui.main.MainActivity


class LetterFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentLetter2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentLetter2Binding.inflate(layoutInflater)

        binding.editTextContentsLetter.setLineColor(R.color.line_yellow)

        return binding.root
    }

}