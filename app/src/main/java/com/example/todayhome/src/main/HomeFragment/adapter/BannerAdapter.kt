package com.example.todayhome.src.main.HomeFragment.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.todayhome.config.home.api.Result
import com.example.todayhome.databinding.ActivityItemLayoutBannerBinding

class BannerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var bannerItemList: List<Result>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = ActivityItemLayoutBannerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BannerViewHolder(binding)
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bannerItemList?.let { bannerItemList ->
            (holder as BannerViewHolder).bind(bannerItemList[position%4])
        }
    }

    //functions
    fun submitList(list: List<Result>?) {
        bannerItemList = list
        notifyDataSetChanged()
    }


    //ViewHolder
    class BannerViewHolder
    constructor(val viewBinding: ActivityItemLayoutBannerBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(bannerItem: Result) {



            Glide
                .with(viewBinding.bannerImage2.context)
                .asBitmap()
                .load(bannerItem.bannerPic)
                .into(viewBinding.bannerImage2)
        }
    }
}