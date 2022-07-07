package com.example.todayhome.src.main.store.adapter


import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.todayhome.config.store.TodaysDeal
import com.example.todayhome.databinding.ActivityPopularItemBinding
import com.example.todayhome.databinding.ActivityScapallBinding


class ScapItemAdapter: ListAdapter<TodaysDeal, ScapItemAdapter.PopularItemViewHolder>(diffUtil) {

    inner class PopularItemViewHolder(private val binding: ActivityScapallBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(view: TodaysDeal) {



            Glide
                .with(binding.thumbnailImageView.context)
                .asBitmap()
                .load(view.hotDealThumbnail)
                .into(binding.thumbnailImageView)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ScapItemAdapter.PopularItemViewHolder {
        return PopularItemViewHolder(
            ActivityScapallBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<TodaysDeal>() {
            override fun areItemsTheSame(oldItem: TodaysDeal, newItem: TodaysDeal): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TodaysDeal, newItem: TodaysDeal): Boolean {
                return oldItem.hotDealThumbnail == newItem.hotDealThumbnail
            }

        }
    }


}