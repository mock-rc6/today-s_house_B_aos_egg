package com.example.todayhome.src.main.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.todayhome.R
import com.example.todayhome.config.store.EventImg
import com.example.todayhome.config.store.TodaysDeal
import com.example.todayhome.databinding.ActivityDetailBinding
import com.example.todayhome.databinding.ActivityDetailPopularBinding

class DetailPopularActivity : AppCompatActivity() {
    private val binding: ActivityDetailPopularBinding by lazy {
        ActivityDetailPopularBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        val TodaysDeal = intent.getParcelableExtra<TodaysDeal>("bannerModel")



        gliderecipeImageView(TodaysDeal)




    }

    private fun gliderecipeImageView(model: TodaysDeal?) {

        if (model != null) {
            Glide.with(binding.coverImageView.context)
                .load(model.hotDealThumbnail.orEmpty())
                .into(binding.coverImageView)
        }

    }


}