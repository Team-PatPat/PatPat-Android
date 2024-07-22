package com.simply407.patpat.ui.chat

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.simply407.patpat.data.Ui_chat
import com.simply407.patpat.databinding.ItemChatfromAiBinding
import com.simply407.patpat.databinding.ItemChattoAiBinding

class ChattingAdapter(val context: Context, val items: MutableList<Ui_chat>) :
    RecyclerView.Adapter<ChattingAdapter.ViewHolder>() {
    companion object {
        private const val FROM = 1
        private const val TO = 2
    }

    override fun getItemViewType(position: Int): Int {
       return if(items[position].isUser) TO else FROM
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChattingAdapter.ViewHolder {
       val inflater=LayoutInflater.from(context)
        return when(viewType){
            FROM->{
                val binding=ItemChatfromAiBinding.inflate(inflater,parent,false)
                ViewHolder(binding)
            }
            TO->{
                val binding=ItemChattoAiBinding.inflate(inflater,parent,false)
                ViewHolder(binding)
            }

            else -> {
                throw IllegalArgumentException("Invalid view type: $viewType")}
        }
    }

    override fun onBindViewHolder(holder: ChattingAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(private val binding : ViewDataBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(items : Ui_chat){
            when(binding){
                is ItemChatfromAiBinding->{
                    binding.itemChatfromme.text=items.message
                    binding.itemProfile.setImageResource(items.profileImg)
                }
                is ItemChattoAiBinding->{
                    binding.itemChattome.text=items.message
                }
            }
        } //bind함수
    }


    fun updateItems(newItems: MutableList<Ui_chat>) {
        val diffCallback = DiffCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }



}




