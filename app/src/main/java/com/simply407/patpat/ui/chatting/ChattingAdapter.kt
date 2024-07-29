package com.simply407.patpat.ui.chatting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simply407.patpat.databinding.ItemChattingCounselorBinding
import com.simply407.patpat.databinding.ItemChattingMyBinding

class ChattingAdapter(private val dataList: List<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ChattingCounselorViewHolder(binding: ItemChattingCounselorBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    inner class ChattingMyViewHolder(binding: ItemChattingMyBinding) : RecyclerView.ViewHolder(binding.root) {

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
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataList[position] % 2) {
            1 -> TYPE_ONE  // 홀수인 경우
            0 -> TYPE_TWO  // 짝수인 경우
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_ONE -> {
                (holder as ChattingCounselorViewHolder)
            }

            TYPE_TWO -> {
                (holder as ChattingMyViewHolder)
            }
        }
    }

    companion object {
        const val TYPE_ONE = 1
        const val TYPE_TWO = 2
    }

}