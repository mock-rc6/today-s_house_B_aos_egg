package com.example.todayhome.src.main.store.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todayhome.databinding.ActivityGridMenuLayout2Binding
import com.example.todayhome.databinding.ActivityGridMenuLayoutBinding
import com.example.todayhome.src.main.store.data.menuGridItem
import com.example.todayhome.src.main.store.data.menuGriditem2

class GridMenuStoreHomeAdapter2 : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var gridItemList: List<menuGriditem2>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = ActivityGridMenuLayout2Binding.inflate(
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
    fun submitList(list: List<menuGriditem2>?) {
        gridItemList = list
        notifyDataSetChanged()
    }


    class GridItemViewHolder(val viewBinding: ActivityGridMenuLayout2Binding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(gridItem: menuGriditem2) {
            viewBinding.imageView.setImageResource(gridItem.image)

        }
    }
}