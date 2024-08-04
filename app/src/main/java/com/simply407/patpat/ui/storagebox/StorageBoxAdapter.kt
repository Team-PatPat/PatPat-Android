package com.simply407.patpat.ui.storagebox

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simply407.patpat.databinding.ItemStorageBoxBinding

class StorageBoxAdapter : RecyclerView.Adapter<StorageBoxAdapter.StorageBoxViewHolder>() {

    inner class StorageBoxViewHolder(private val binding: ItemStorageBoxBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageBoxViewHolder {
        val binding = ItemStorageBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StorageBoxViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: StorageBoxViewHolder, position: Int) {

    }

}