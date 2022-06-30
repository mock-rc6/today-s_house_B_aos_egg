package com.example.todayhome.src.main.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.todayhome.R
import com.example.todayhome.config.store.EventImg
import com.example.todayhome.config.store.TodaysDeal
import com.example.todayhome.databinding.ActivityDetailBinding
import com.example.todayhome.databinding.ActivityStoreHomeTodayDillBinding

class StoreHomeTodayDillActivity : AppCompatActivity() {
    private val binding: ActivityStoreHomeTodayDillBinding by lazy {
        ActivityStoreHomeTodayDillBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        val model = intent.getParcelableExtra<TodaysDeal>("todayModel")



        gliderecipeImageView(model)




    }

    private fun gliderecipeImageView(model: TodaysDeal?) {

        if (model != null) {
            Glide.with(binding.coverImageView.context)
                .load(model.hotDealThumbnail.orEmpty())
                .into(binding.coverImageView)
        }

    }


}