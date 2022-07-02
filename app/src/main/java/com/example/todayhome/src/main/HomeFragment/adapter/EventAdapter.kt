package com.example.todayhome.src.main.HomeFragment.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.todayhome.config.home.api.Result
import com.example.todayhome.databinding.ItemEventBinding

class EventAdapter : ListAdapter<Result, EventAdapter.CookItemViewHolder>(diffUtil) {

    inner class CookItemViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Result) {
            binding.timeTextView.text = event.due.toString()

            Glide
                .with(binding.eventImageView.context)
                .load(event.bannerPic)
                .into(binding.eventImageView)

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventAdapter.CookItemViewHolder {
        return CookItemViewHolder(
            ItemEventBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CookItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.bannerPic == newItem.bannerPic
            }

        }
    }


}