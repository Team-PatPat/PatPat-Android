package com.simply407.patpat.ui.storagebox

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.simply407.patpat.R
import com.simply407.patpat.data.model.CreateLetterResponse
import com.simply407.patpat.databinding.ItemStorageBoxBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StorageBoxAdapter(
    private var counselorList: List<String>,
    private var allLettersDataList: List<CreateLetterResponse>,
    private val letterClickListener: LetterClickListener
) : RecyclerView.Adapter<StorageBoxAdapter.StorageBoxViewHolder>() {

    inner class StorageBoxViewHolder(private val binding: ItemStorageBoxBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(letter: CreateLetterResponse) {

            // 상담사 별 프로필, 제목, 편지지 색상 설정
            val counselorIndex = counselorList.indexOf(letter.counselorId)

            when (counselorIndex) {
                0 -> {
                    binding.cardViewStorageBox.backgroundTintList = ContextCompat.getColorStateList(binding.root.context, R.color.letter_yellow)
                    binding.imageViewStorageBox.setImageResource(R.drawable.ic_profile_boknam)
                    binding.textViewLetterTitleStorageBox.text = "복남이의 편지"
                    binding.viewLineStorageBox.setBackgroundResource(R.color.line_yellow)
                }
                1 -> {
                    binding.cardViewStorageBox.backgroundTintList = ContextCompat.getColorStateList(binding.root.context, R.color.letter_blue)
                    binding.imageViewStorageBox.setImageResource(R.drawable.ic_doctor_profile)
                    binding.textViewLetterTitleStorageBox.text = "닥터 냉철한의 편지"
                    binding.viewLineStorageBox.setBackgroundResource(R.color.line_blue)
                }
                2 -> {
                    binding.cardViewStorageBox.backgroundTintList = ContextCompat.getColorStateList(binding.root.context, R.color.letter_red)
                    binding.imageViewStorageBox.setImageResource(R.drawable.ic_kwak_profile)
                    binding.textViewLetterTitleStorageBox.text = "곽두팔의 편지"
                    binding.viewLineStorageBox.setBackgroundResource(R.color.line_red)
                }
                3 -> {
                    binding.cardViewStorageBox.backgroundTintList = ContextCompat.getColorStateList(binding.root.context, R.color.letter_green)
                    binding.imageViewStorageBox.setImageResource(R.drawable.ic_coco_profile)
                    binding.textViewLetterTitleStorageBox.text = "코코의 편지"
                    binding.viewLineStorageBox.setBackgroundResource(R.color.line_green)
                }
            }

            binding.textViewLetterContentStorageBox.text = letter.content
            binding.textViewLetterDateStorageBox.text = formatDate(letter.createdAt)

            // 전체 보기 클릭
            binding.linearLayoutShowAllStorageBox.setOnClickListener {
                letterClickListener.onClickLetter(counselorIndex, letter)
            }
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

    fun updateData(newCounselorList: List<String>, newLettersDataList: List<CreateLetterResponse>) {
        counselorList = newCounselorList
        allLettersDataList = newLettersDataList
        notifyDataSetChanged()
    }

    interface LetterClickListener {
        fun onClickLetter(counselorIndex: Int, letter: CreateLetterResponse)
    }

}