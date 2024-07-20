package com.simply407.patpat.ui.chat

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simply407.patpat.data.Ui_chat

class ChattingAdapter(val context : Context, val items : List<Ui_chat>):
    RecyclerView.Adapter<ChattingAdapter.ViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChattingAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ChattingAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(itemView : View)  : RecyclerView.ViewHolder(itemView){

    }

}