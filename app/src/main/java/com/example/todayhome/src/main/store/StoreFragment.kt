package com.example.ourtable.store


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todayhome.R
import com.example.todayhome.databinding.FragmentHomeBinding
import com.example.todayhome.databinding.FragmentStoreBinding

import com.example.todayhome.src.main.store.adapter.TabLayoutViewPager2AdapterStore
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class StoreFragment : Fragment(R.layout.fragment_store) {
    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoreBinding.inflate(inflater, container, false)
        tabLayoutViewPage2()


        return binding.root
    }

    private fun tabLayoutViewPage2() {
        val adapter = TabLayoutViewPager2AdapterStore(this)
        binding.homeTabLayoutViewPager2.adapter = adapter

        val tabName = arrayOf("스토어홈", "베스트", "오늘의딜", "키친빅세일","주말반짝세일","오!굿즈","빠른배송","프리미엄","기획전")


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


