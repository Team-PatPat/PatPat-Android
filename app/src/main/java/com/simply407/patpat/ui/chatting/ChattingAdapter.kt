package com.simply407.patpat.ui.chatting

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simply407.patpat.R
import com.simply407.patpat.data.model.MessageInfo
import com.simply407.patpat.databinding.ItemChattingCounselorBinding
import com.simply407.patpat.databinding.ItemChattingMyBinding

class ChattingAdapter(private var allMessagesDataList: List<MessageInfo>, private val counselorId: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ChattingCounselorViewHolder(private val binding: ItemChattingCounselorBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(message: MessageInfo) {

            // 상담사 프로필 이미지 설정
            when (counselorId) {
                0 -> {
                    binding.imageViewChattingCounselorItem.setImageResource(R.drawable.ic_profile_boknam)
                }
                1 -> {
                    binding.imageViewChattingCounselorItem.setImageResource(R.drawable.ic_doctor_profile)
                }
                2 -> {
                    binding.imageViewChattingCounselorItem.setImageResource(R.drawable.ic_kwak_profile)
                }
                3 -> {
                    binding.imageViewChattingCounselorItem.setImageResource(R.drawable.ic_coco_profile)
                }
            }

            // 상담사 메시지 설정
            binding.textViewChattingCounselorItem.text = message.content
        }

    }

    inner class ChattingMyViewHolder(private val binding: ItemChattingMyBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(message: MessageInfo) {

            // 유저 말풍선 색상 설정
            when (counselorId) {
                0 -> {
                    binding.textViewChattingMyItem.setBackgroundResource(R.drawable.bg_bok_chatting_body)
                    binding.imageViewTailChattingMyItem.setImageResource(R.drawable.ic_bok_chatting_tail)
                }
                1 -> {
                    binding.textViewChattingMyItem.setBackgroundResource(R.drawable.bg_doctor_chatting_body)
                    binding.imageViewTailChattingMyItem.setImageResource(R.drawable.ic_doctor_chatting_tail)
                }
                2 -> {
                    binding.textViewChattingMyItem.setBackgroundResource(R.drawable.bg_kwak_chatting_body)
                    binding.imageViewTailChattingMyItem.setImageResource(R.drawable.ic_kwak_chatting_tail)
                }
                3 -> {
                    binding.textViewChattingMyItem.setBackgroundResource(R.drawable.bg_coco_chatting_body)
                    binding.imageViewTailChattingMyItem.setImageResource(R.drawable.ic_coco_chatting_tail)
                }
            }

            // 유저 메시지 설정
            binding.textViewChattingMyItem.text = message.content
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_ONE -> {
                val binding = ItemChattingCounselorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ChattingCounselorViewHolder(binding)
            }
            TYPE_TWO -> {
                val binding = ItemChattingMyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ChattingMyViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return allMessagesDataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (allMessagesDataList[position].role) {
            "ASSISTANT" -> TYPE_ONE  // 상담사 인 경우
            "USER" -> TYPE_TWO  // 유저 인 경우
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_ONE -> {
                (holder as ChattingCounselorViewHolder).bind(allMessagesDataList[position])
            }

            TYPE_TWO -> {
                (holder as ChattingMyViewHolder).bind(allMessagesDataList[position])
            }
        }
    }

    fun updateMessages(newMessages: List<MessageInfo>) {
        allMessagesDataList = newMessages
        notifyDataSetChanged()

        Log.d("ChattingAdapter", "allMessagesDataList : $allMessagesDataList")
    }

    companion object {
        const val TYPE_ONE = 1
        const val TYPE_TWO = 2
    }

}