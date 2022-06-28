package com.example.todayhome.src.main.store.adapter




import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.todayhome.R
import com.example.todayhome.config.store.EventImg



class ViewPage2StoreHomeAdapter(private val itemClickedListener: (EventImg) -> Unit): ListAdapter<EventImg, ViewPage2StoreHomeAdapter.ItemViewHolder>(differ) {
    inner class ItemViewHolder(val view:View):RecyclerView.ViewHolder(view){
        fun bind(row: EventImg){
            val thumbnailImageView = view.findViewById<ImageView>(R.id.bannerImage)

            view.rootView.setOnClickListener {
                itemClickedListener(row)
            }

            Glide
                .with(thumbnailImageView.context)
                .load(row.storeEventImgUrl)
                .into(thumbnailImageView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater.inflate(R.layout.activity_store_home_item_layout_banner,parent,false))
    }



    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.bind(currentList[position])
    }

    companion object{
        val differ = object :DiffUtil.ItemCallback<EventImg>(){
            override fun areItemsTheSame(oldItem: EventImg, newItem: EventImg): Boolean {
                return oldItem.storeEventImgUrl ==newItem.storeEventImgUrl
            }

            override fun areContentsTheSame(oldItem: EventImg, newItem: EventImg): Boolean {
                return oldItem == newItem
            }

        }
    }
}
