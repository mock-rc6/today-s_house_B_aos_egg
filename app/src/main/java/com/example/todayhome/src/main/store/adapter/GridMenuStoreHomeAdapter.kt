package com.example.todayhome.src.main.store.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todayhome.databinding.ActivityGridMenuLayoutBinding
import com.example.todayhome.src.main.store.data.menuGridItem

class GridMenuStoreHomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var gridItemList: List<menuGridItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = ActivityGridMenuLayoutBinding.inflate(
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
    fun submitList(list: List<menuGridItem>?) {
        gridItemList = list
        notifyDataSetChanged()
    }


    class GridItemViewHolder(val viewBinding: ActivityGridMenuLayoutBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(gridItem: menuGridItem) {
            viewBinding.ivGridImage.setImageResource(gridItem.image)
            viewBinding.tvGridTitle.text = gridItem.title
        }
    }
}