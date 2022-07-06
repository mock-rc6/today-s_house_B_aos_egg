package com.example.todayhome.src.main.store.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.todayhome.R
import com.example.todayhome.config.store.TodaysDeal

class ReviewViewPageAdapter : ListAdapter<TodaysDeal, ReviewViewPageAdapter.ItemViewHolder>(differ) {
    inner class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(row: TodaysDeal) {
            val thumbnailImageView = view.findViewById<ImageView>(R.id.reviewImageView)

            Glide
                .with(thumbnailImageView.context)
                .asBitmap()
                .load(row.hotDealThumbnail)
                .into(thumbnailImageView)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater.inflate(R.layout.review_item, parent, false))
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.bind(currentList[position])
    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<TodaysDeal>() {
            override fun areItemsTheSame(oldItem: TodaysDeal, newItem: TodaysDeal): Boolean {
                return oldItem.hotDealThumbnail == newItem.hotDealThumbnail
            }

            override fun areContentsTheSame(oldItem: TodaysDeal, newItem: TodaysDeal): Boolean {
                return oldItem == newItem
            }

        }
    }
}
