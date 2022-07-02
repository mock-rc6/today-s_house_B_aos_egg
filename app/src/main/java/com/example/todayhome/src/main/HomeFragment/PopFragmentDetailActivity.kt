package com.example.todayhome.src.main.HomeFragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide

import com.example.todayhome.config.home.api.Result

import com.example.todayhome.databinding.ActivityPopFragmentDetailBinding

class PopFragmentDetailActivity : AppCompatActivity() {
    private val binding: ActivityPopFragmentDetailBinding by lazy {
        ActivityPopFragmentDetailBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val model = intent.getParcelableExtra<Result>("bannerModel")



        gliderecipeImageView(model)


    }

    private fun gliderecipeImageView(model: Result?) {

        if (model != null) {
            Glide.with(binding.coverImageView.context)
                .load(model.bannerPic.orEmpty())
                .into(binding.coverImageView)
        }

    }
}


