package com.example.todayhome.src.main.store.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.todayhome.config.store.itemdetail.Review
import com.example.todayhome.databinding.ActivityReviewListBinding

class reviewListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private var bannerItemList: List<Review>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = ActivityReviewListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BannerViewHolder(binding)
    }

    override fun getItemCount(): Int = 3


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bannerItemList?.let { bannerItemList ->
            (holder as BannerViewHolder).bind(bannerItemList[position])
        }
    }

    //functions
    fun submitList(list: List<Review>?) {
        bannerItemList = list!!.reversed()
        notifyDataSetChanged()
    }


    //ViewHolder
    class BannerViewHolder
    constructor(val viewBinding: ActivityReviewListBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(bannerItem: Review) {


            viewBinding.userName.text = bannerItem.userName.toString()
            viewBinding.reviewRatingBar.rating = bannerItem.score!!.toFloat()
            viewBinding.createdAtTextView.text = bannerItem.createdAt.toString()
            viewBinding.buyAtTextView.text = bannerItem.buyAt.toString()
            viewBinding.description.text = bannerItem.description.toString()

            if (bannerItem.img == null) {
                Log.d("코난null", "bannerItem.img")
                viewBinding.cardView.visibility = View.GONE
            } else {
                viewBinding.cardView.visibility = View.VISIBLE
                Glide
                    .with(viewBinding.reviewImageView.context)
                    .asBitmap()
                    .load(bannerItem.img)
                    .into(viewBinding.reviewImageView)
            }




            Glide
                .with(viewBinding.profilePicImageView.context)
                .asBitmap()
                .load(bannerItem.profilePic)
                .into(viewBinding.profilePicImageView)
        }
    }
}
