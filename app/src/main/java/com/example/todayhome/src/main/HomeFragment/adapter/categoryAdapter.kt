package com.example.todayhome.src.main.HomeFragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.todayhome.R
import com.example.todayhome.config.store.TodaysDeal
import com.example.todayhome.databinding.ActivityCategotyItemBinding
import com.example.todayhome.databinding.ActivityItemLayoutBannerBinding

class categoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val ITEM_COUNT = 16
    }

    private var bannerItemList: List<categoryItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = ActivityCategotyItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BannerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return ITEM_COUNT
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bannerItemList?.let { bannerItemList ->
            (holder as BannerViewHolder).bind(bannerItemList[position])
        }
    }

    //functions
    fun submitList(list: List<categoryItem>?) {
        bannerItemList = list
        notifyDataSetChanged()
    }


    //ViewHolder
    class BannerViewHolder
    constructor(val viewBinding:ActivityCategotyItemBinding ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(bannerItem: categoryItem) {
            viewBinding.bannerImage.setImageResource(bannerItem.image)
        }
    }
}
