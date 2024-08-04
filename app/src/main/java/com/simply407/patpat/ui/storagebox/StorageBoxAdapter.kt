package com.simply407.patpat.ui.storagebox

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simply407.patpat.data.model.CreateLetterResponse
import com.simply407.patpat.databinding.ItemStorageBoxBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StorageBoxAdapter(private val allLettersDataList: List<CreateLetterResponse>) : RecyclerView.Adapter<StorageBoxAdapter.StorageBoxViewHolder>() {

    inner class StorageBoxViewHolder(private val binding: ItemStorageBoxBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(letter: CreateLetterResponse) {
            binding.textViewLetterContentStorageBox.text = letter.content
            binding.textViewLetterDateStorageBox.text = formatDate(letter.createdAt)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageBoxViewHolder {
        val binding = ItemStorageBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StorageBoxViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return allLettersDataList.size
    }

    override fun onBindViewHolder(holder: StorageBoxViewHolder, position: Int) {
        holder.bind(allLettersDataList[position])
    }

    private fun formatDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())

        return try {
            val date: Date = inputFormat.parse(dateString) ?: Date()
            outputFormat.format(date)
        } catch (e: Exception) {
            dateString // 변환 실패 시 원본 문자열 반환
        }
    }

}