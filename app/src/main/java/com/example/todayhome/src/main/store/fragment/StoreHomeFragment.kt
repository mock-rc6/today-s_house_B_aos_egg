package com.example.todayhome.src.main.store.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.todayhome.R
import com.example.todayhome.databinding.FragmentHomeBinding
import com.example.todayhome.databinding.FragmentStoreHomeBinding
import com.example.todayhome.src.main.store.adapter.viewPage2StoreHomeAdapter
import com.example.todayhome.src.main.store.data.StoreHomeBannerItemList
import com.example.todayhome.src.main.store.storeHomeViewModel


class StoreHomeFragment : Fragment() {


    private var _binding: FragmentStoreHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPagerAdapter: viewPage2StoreHomeAdapter
    private lateinit var viewModel: storeHomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoreHomeBinding.inflate(inflater, container, false)


        viewModel = ViewModelProvider(this).get(storeHomeViewModel::class.java)
        viewModel.setBannerItems(StoreHomeBannerItemList)

        initViewPager2()
        subscribeObservers()
        return binding.root
    }



    private fun initViewPager2() {
        binding.viewPager2.apply {
            viewPagerAdapter = viewPage2StoreHomeAdapter()
            adapter = viewPagerAdapter
        }
    }

    private fun subscribeObservers() {
        viewModel.bannerItemList.observe(viewLifecycleOwner, Observer { bannerItemList ->
            viewPagerAdapter.submitList(bannerItemList)
        })

    }


}