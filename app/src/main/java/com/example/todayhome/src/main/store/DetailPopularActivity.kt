package com.example.todayhome.src.main.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.todayhome.config.store.TodaysDeal
import com.example.todayhome.databinding.ActivityDetailPopularBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailPopularActivity : AppCompatActivity() {
    private val binding: ActivityDetailPopularBinding by lazy {
        ActivityDetailPopularBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val TodaysDeal = intent.getParcelableExtra<TodaysDeal>("bannerModel")
        gliderecipeImageView(TodaysDeal)
        tabLayoutViewPage2()

    }

    private fun gliderecipeImageView(model: TodaysDeal?) {

        if (model != null) {
            Glide.with(binding.coverImageView.context)
                .load(model.hotDealThumbnail.orEmpty())
                .into(binding.coverImageView)
        }

    }

    private fun tabLayoutViewPage2() {
        val adapter = detailPoplurTabLayoutViewPagerAdapter(this)
        binding.viewPager2.adapter = adapter

        val tabName = arrayOf("상품정보", "리뷰","문의","배송/환불","추천" )


        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = tabName[position]
        }.attach()



        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager2.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })




    }

}



