package com.simply407.patpat.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simply407.patpat.data.model.SharedPreferencesManager
import com.simply407.patpat.databinding.ItemOnboardingOneBinding
import com.simply407.patpat.databinding.ItemOnboardingTwoBinding

class OnboardingAdapter(private val clickListener: OnboardingClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class OnboardingViewHolderTypeOne(binding: ItemOnboardingOneBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    inner class OnboardingViewHolderTypeTwo(private val binding: ItemOnboardingTwoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.buttonItemOnboardingTwo.setOnClickListener {
                SharedPreferencesManager.setOnboardingShown(true)
                clickListener.onOnboardingButtonClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_ONE -> {
                val binding = ItemOnboardingOneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                OnboardingViewHolderTypeOne(binding)
            }
            TYPE_TWO -> {
                val binding = ItemOnboardingTwoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                OnboardingViewHolderTypeTwo(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_ONE
            1 -> TYPE_TWO
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_ONE -> {

            }
            TYPE_TWO -> {
                (holder as OnboardingViewHolderTypeTwo).bind()
            }
        }
    }

    companion object {
        const val TYPE_ONE = 1
        const val TYPE_TWO = 2
    }

    interface OnboardingClickListener {
        fun onOnboardingButtonClick()
    }

}