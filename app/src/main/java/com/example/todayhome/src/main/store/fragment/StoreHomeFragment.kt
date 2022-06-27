package com.example.todayhome.src.main.store.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.todayhome.databinding.FragmentStoreHomeBinding
import com.example.todayhome.src.main.store.adapter.GridMenuStoreHomeAdapter
import com.example.todayhome.src.main.store.adapter.GridMenuStoreHomeAdapter2
import com.example.todayhome.src.main.store.adapter.viewPage2StoreHomeAdapter
import com.example.todayhome.src.main.store.data.StoreHomeBannerItemList
import com.example.todayhome.src.main.store.data.StoreHomeGridItemList
import com.example.todayhome.src.main.store.data.StoreHomeGridItemList2
import com.example.todayhome.src.main.store.storeHomeViewModel


class StoreHomeFragment : Fragment() {


    private var _binding: FragmentStoreHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPagerAdapter: viewPage2StoreHomeAdapter
    private lateinit var viewModel: storeHomeViewModel

    private lateinit var gridRecyclerViewAdapter: GridMenuStoreHomeAdapter
    private lateinit var gridRecyclerViewAdapter2: GridMenuStoreHomeAdapter2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoreHomeBinding.inflate(inflater, container, false)


        viewModel = ViewModelProvider(this).get(storeHomeViewModel::class.java)
        viewModel.setBannerItems(StoreHomeBannerItemList)
        viewModel.setGridItems(StoreHomeGridItemList)
        viewModel.setGridItems2(StoreHomeGridItemList2)


        initViewPager2()
        subscribeObservers()

        return binding.root
    }



    private fun initViewPager2() {
        binding.viewPager2.apply {
            viewPagerAdapter = viewPage2StoreHomeAdapter()
            adapter = viewPagerAdapter
            binding.viewPager2Indicator.attachTo(binding.viewPager2)


        }

        binding.menuRecyclerView.apply {
            gridRecyclerViewAdapter = GridMenuStoreHomeAdapter()
            layoutManager = GridLayoutManager(context, 5)
            adapter = gridRecyclerViewAdapter
        }

        binding.itemMenuRecyclerView.apply {
            gridRecyclerViewAdapter2 = GridMenuStoreHomeAdapter2()
            layoutManager = GridLayoutManager(context, 4)
            adapter = gridRecyclerViewAdapter2
        }

    }

    private fun subscribeObservers() {
        viewModel.bannerItemList.observe(viewLifecycleOwner, Observer { bannerItemList ->
            viewPagerAdapter.submitList(bannerItemList)
        })

        viewModel.gridItemList.observe(viewLifecycleOwner, Observer {gridItemList->
            gridRecyclerViewAdapter.submitList(gridItemList)
        })

        viewModel.gridItemList2.observe(viewLifecycleOwner, Observer {gridItemList2->
            gridRecyclerViewAdapter2.submitList(gridItemList2)
        })


    }



}