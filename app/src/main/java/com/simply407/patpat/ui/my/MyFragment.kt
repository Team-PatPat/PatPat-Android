package com.simply407.patpat.ui.my

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.simply407.patpat.R
import com.simply407.patpat.databinding.FragmentMyBinding
import com.simply407.patpat.ui.main.MainActivity


class MyFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentMyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentMyBinding.inflate(layoutInflater)

        return binding.root
    }

}