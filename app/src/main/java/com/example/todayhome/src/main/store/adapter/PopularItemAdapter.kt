package com.example.todayhome.src.main.store.adapter


import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.todayhome.config.store.TodaysDeal
import com.example.todayhome.databinding.ActivityPopularItemBinding



class PopularItemAdapter(private val itemClickedListener: (TodaysDeal) -> Unit) :
    ListAdapter<TodaysDeal, PopularItemAdapter.PopularItemViewHolder>(diffUtil) {

    inner class PopularItemViewHolder(private val binding: ActivityPopularItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(view: TodaysDeal) {



            binding.companyName.text = view.companyName.toString()

            binding.itemName.text = view.itemName.toString()
            binding.SaleTextView.text = view.saleRate.toString()
            binding.priceTextView.text = view.price.toString()
            binding.scoreTextView.text = view.score.toString().substring(0 until 3)
            binding.reviewCountTextView.text = view.reviewNum.toString()

            binding.root.setOnClickListener {
                itemClickedListener(view)
            }



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
    ): PopularItemAdapter.PopularItemViewHolder {
        return PopularItemViewHolder(
            ActivityPopularItemBinding.inflate(
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