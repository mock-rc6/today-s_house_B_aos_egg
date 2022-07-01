package com.example.todayhome.src.main.store.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todayhome.databinding.ActivityPopularsearchBinding
import com.example.todayhome.src.main.store.data.PopularSearch


class PopularSearchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var gridItemList: List<PopularSearch>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = ActivityPopularsearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GridItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return gridItemList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        gridItemList?.let {
            (holder as GridItemViewHolder).bind(it[position])
        }
    }

    //functions
    fun submitList(list: List<PopularSearch>?) {
        gridItemList = list
        notifyDataSetChanged()
    }


    class GridItemViewHolder(val viewBinding: ActivityPopularsearchBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(gridItem: PopularSearch) {
            viewBinding.numberTextView.text = gridItem.num
            viewBinding.imageView.setImageResource(gridItem.image)
            viewBinding.itemTextView.text = gridItem.name
        }
    }
}