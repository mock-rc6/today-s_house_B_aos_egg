package com.example.todayhome.src.main.store.adapter


import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todayhome.databinding.ActivityStoreHomeItemLayoutBannerBinding

import com.example.todayhome.src.main.store.StoreHomeBannerItem

class viewPage2StoreHomeAdapter  : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val ITEM_COUNT = 10
    }

    private var bannerItemList: List<StoreHomeBannerItem >? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = ActivityStoreHomeItemLayoutBannerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BannerViewHolder(binding)
    }

    override fun getItemCount(): Int =Int.MAX_VALUE

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bannerItemList?.let { bannerItemList ->
            (holder as BannerViewHolder).bind(bannerItemList[position%3])
        }
    }

    //functions
    fun submitList(list: List<StoreHomeBannerItem>?) {
        bannerItemList = list
        notifyDataSetChanged()
    }


    //ViewHolder
    class BannerViewHolder
    constructor(val viewBinding:ActivityStoreHomeItemLayoutBannerBinding ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(bannerItem: StoreHomeBannerItem) {
            viewBinding.bannerImage.setImageResource(bannerItem.image)
        }
    }
}
