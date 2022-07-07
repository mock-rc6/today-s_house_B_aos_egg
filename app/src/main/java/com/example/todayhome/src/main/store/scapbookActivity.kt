package com.example.todayhome.src.main.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todayhome.R
import com.example.todayhome.databinding.ActivityMainBinding
import com.example.todayhome.databinding.ActivityScapbookBinding
import com.example.todayhome.src.main.store.adapter.ItemDetailAdapter
import com.example.todayhome.src.main.store.adapter.scapTabAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class scapbookActivity : AppCompatActivity() {
    private val binding: ActivityScapbookBinding by lazy {
        ActivityScapbookBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tabLayoutViewPage2()

    }

    private fun tabLayoutViewPage2() {
        val adapter = scapTabAdapter(this)
        binding.homeTabLayoutViewPager2.adapter = adapter

        val tabName = arrayOf("모두", "폴더", "상품")


        TabLayoutMediator(binding.tabLayout, binding.homeTabLayoutViewPager2) { tab, position ->
            tab.text = tabName[position]
        }.attach()



        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.homeTabLayoutViewPager2.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }
}