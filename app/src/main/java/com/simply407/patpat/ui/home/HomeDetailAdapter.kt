package com.simply407.patpat.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simply407.patpat.R
import com.simply407.patpat.databinding.ItemCounselorsDetailBinding

class HomeDetailAdapter : RecyclerView.Adapter<HomeDetailAdapter.HomeDetailViewHolder>() {

    inner class HomeDetailViewHolder(private val binding: ItemCounselorsDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(index: Int) {
            when (index) {
                0 -> {
                    binding.imageViewCounselorsDetail.setImageResource(R.drawable.img_boknam_detail)
                    binding.textViewNameCounselorsDetail.text = "복남이"
                    binding.textViewIntroductionCounselor.text = binding.root.context.getString(R.string.bokname_introduction_detail)
                }
                1 -> {
                    binding.imageViewCounselorsDetail.setImageResource(R.drawable.img_doctor_detail)
                    binding.textViewNameCounselorsDetail.text = "닥터 냉철한"
                    binding.textViewIntroductionCounselor.text = binding.root.context.getString(R.string.doctor_introduction_detail)

                    // 임시 화면 설정
                    binding.imageViewStateCounselorsDetail.setImageResource(R.drawable.ic_thumbs_up)
                    binding.textViewStateCounselorsDetail.text = "MBTI 추천"
                }
                2 -> {
                    binding.imageViewCounselorsDetail.setImageResource(R.drawable.img_kwak_detail)
                    binding.textViewNameCounselorsDetail.text = "곽두팔"
                    binding.textViewIntroductionCounselor.text = binding.root.context.getString(R.string.kwak_introduction_detail)

                    // 임시 화면 설정
                    binding.linearLayoutStateCounselorsDetail.visibility = View.GONE
                }
                3 -> {
                    binding.imageViewCounselorsDetail.setImageResource(R.drawable.img_coco_detail)
                    binding.textViewNameCounselorsDetail.text = "코코"
                    binding.textViewIntroductionCounselor.text = binding.root.context.getString(R.string.coco_introduction_detail)

                    // 임시 화면 설정
                    binding.linearLayoutStateCounselorsDetail.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeDetailViewHolder {
        val binding = ItemCounselorsDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeDetailViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    override fun onBindViewHolder(holder: HomeDetailViewHolder, position: Int) {
        val index = position % 4
        holder.bind(index)
    }
}