package com.simply407.patpat.ui.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simply407.patpat.R

class OnboardingAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class OnboardingViewHolderTypeOne(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    inner class OnboardingViewHolderTypeTwo(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_ONE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_onboarding_one, parent, false)
                OnboardingViewHolderTypeOne(view)
            }
            TYPE_TWO -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_onboarding_two, parent, false)
                OnboardingViewHolderTypeTwo(view)
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

            }
        }
    }

    companion object {
        const val TYPE_ONE = 1
        const val TYPE_TWO = 2
    }

}