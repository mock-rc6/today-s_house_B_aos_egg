package com.example.todayhome.src.main.store.adapter

import android.util.Base64
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

class ViewPager2TodayDillAdapter(private val itemClickedListener: (TodaysDeal) -> Unit) :
    ListAdapter<TodaysDeal, ViewPager2TodayDillAdapter.ItemViewHolder>(differ) {
    inner class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(row: TodaysDeal) {
            val thumbnailImageView = view.findViewById<ImageView>(R.id.thumbnailImageView)
            val companyName = view.findViewById<TextView>(R.id.companyName)
            val due = view.findViewById<TextView>(R.id.due)
            val itemName = view.findViewById<TextView>(R.id.itemName)
            val saleRate = view.findViewById<TextView>(R.id.SaleTextView)
            val price = view.findViewById<TextView>(R.id.priceTextView)
            val score = view.findViewById<TextView>(R.id.scoreTextView)
            val reviewNum = view.findViewById<TextView>(R.id.reviewCountTextView)

            companyName.text = row.companyName.toString()
            due.text = row.due.toString()
            itemName.text = row.itemName.toString()
            saleRate.text = row.saleRate.toString()
            price.text = row.price.toString()
            score.text = row.score.toString().substring(0 until 3)
            reviewNum.text = row.reviewNum.toString()

            view.rootView.setOnClickListener {
                itemClickedListener(row)
            }



            Glide
                .with(thumbnailImageView.context)
                .asBitmap()
                .load(row.hotDealThumbnail)
                .into(thumbnailImageView)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater.inflate(R.layout.activity_today_dill, parent, false))
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