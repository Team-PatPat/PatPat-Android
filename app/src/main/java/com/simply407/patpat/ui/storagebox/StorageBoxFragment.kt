package com.simply407.patpat.ui.storagebox

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.simply407.patpat.R
import com.simply407.patpat.data.model.SharedPreferencesManager
import com.simply407.patpat.databinding.FragmentStorageBoxBinding
import com.simply407.patpat.ui.home.HomeViewModel
import com.simply407.patpat.ui.letter.LetterViewModel
import com.simply407.patpat.ui.main.MainActivity


class StorageBoxFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentStorageBoxBinding

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var letterViewModel: LetterViewModel

    private var counselorIdList: MutableList<String> = mutableListOf()

    val TAG = "StorageBoxFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentStorageBoxBinding.inflate(layoutInflater, container, false)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        letterViewModel = ViewModelProvider(this)[LetterViewModel::class.java]

        homeViewModel.getCounselors(SharedPreferencesManager.getUserAccessToken()!!)
        letterViewModel.getAllLetters(SharedPreferencesManager.getUserAccessToken()!!, 1, 100, false)

        initUi()
        observeData()

        return binding.root
    }

    private fun initUi() {
        binding.run {

            switchStorageBox.run {
                thumbTintList = ContextCompat.getColorStateList(requireContext(), R.color.orange)
                trackTintList = ContextCompat.getColorStateList(requireContext(), R.color.grayScale200)

                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        textViewAllStorageBox.setTextColor(ContextCompat.getColor(requireContext(), R.color.grayScale400))
                        textViewFavoriteStorageBox.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                    } else {
                        textViewAllStorageBox.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        textViewFavoriteStorageBox.setTextColor(ContextCompat.getColor(requireContext(), R.color.grayScale400))
                    }
                }
            }

            recyclerViewStorageBox.run {
                adapter = StorageBoxAdapter(emptyList(), emptyList())
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
            }

        }
    }

    private fun observeData() {
        homeViewModel.counselorList.observe(viewLifecycleOwner) { result ->
            result.onSuccess { counselorList ->
                mainActivity.logLongMessage(TAG, "getCounselors 성공: $counselorList")
                counselorList.forEach { counselor ->
                    counselorIdList.add(counselor.id)
                }
            }
            result.onFailure { error ->
                mainActivity.logLongMessage(TAG, "getCounselors 실패: $error")
            }
        }

        letterViewModel.allLettersList.observe(viewLifecycleOwner) { result ->
            result.onSuccess { allLettersList ->
                mainActivity.logLongMessage(TAG, "getAllLetters 성공: $allLettersList")
                allLettersList.data.forEach { letter ->
                    Log.d(TAG, "letter : $letter")
                }
                binding.recyclerViewStorageBox.adapter = StorageBoxAdapter(counselorIdList, allLettersList.data)
            }

            result.onFailure { error ->
                mainActivity.logLongMessage(TAG, "getAllLetters 실패: $error")
            }
        }
    }

}