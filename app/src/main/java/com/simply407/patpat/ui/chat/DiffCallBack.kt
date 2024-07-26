package com.simply407.patpat.ui.chat

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.simply407.patpat.data.Ui_chat


class DiffCallback(
    private val oldList: MutableList<Ui_chat>,
    private val newList: MutableList<Ui_chat>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].message == newList[newItemPosition].message
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }


}