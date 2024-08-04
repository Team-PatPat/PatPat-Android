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
import com.simply407.patpat.ui.letter.LetterViewModel
import com.simply407.patpat.ui.main.MainActivity


class StorageBoxFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    private lateinit var binding: FragmentStorageBoxBinding

    private lateinit var letterViewModel: LetterViewModel

    val TAG = "StorageBoxFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainActivity = activity as MainActivity
        binding = FragmentStorageBoxBinding.inflate(layoutInflater, container, false)

        letterViewModel = ViewModelProvider(this)[LetterViewModel::class.java]

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
                adapter = StorageBoxAdapter(emptyList())
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
            }

        }
    }

    private fun observeData() {
        letterViewModel.allLettersList.observe(viewLifecycleOwner) { result ->
            result.onSuccess { allLettersList ->
                mainActivity.logLongMessage(TAG, "getAllLetters 성공: $allLettersList")
                allLettersList.data.forEach { letter ->
                    Log.d(TAG, "letter : $letter")
                }
                binding.recyclerViewStorageBox.adapter = StorageBoxAdapter(allLettersList.data)
            }

            result.onFailure { error ->
                mainActivity.logLongMessage(TAG, "getAllLetters 실패: $error")
            }
        }
    }

}