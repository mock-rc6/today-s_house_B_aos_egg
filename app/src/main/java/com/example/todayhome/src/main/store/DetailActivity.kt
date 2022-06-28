package com.example.todayhome.src.main.store



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.bumptech.glide.Glide

import com.example.todayhome.config.store.EventImg
import com.example.todayhome.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        val model = intent.getParcelableExtra<EventImg>("cookModel")



        gliderecipeImageView(model)




    }

    private fun gliderecipeImageView(model: EventImg?) {

        if (model != null) {
            Glide.with(binding.coverImageView.context)
                .load(model.storeEventImgUrl.orEmpty())
                .into(binding.coverImageView)
        }

    }


}