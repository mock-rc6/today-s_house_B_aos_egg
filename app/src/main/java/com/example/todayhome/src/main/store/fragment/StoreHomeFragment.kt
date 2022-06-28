package com.example.todayhome.src.main.store.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.todayhome.R
import com.example.todayhome.config.store.api.StoreService
import com.example.todayhome.config.store.storeAPI
import com.example.todayhome.databinding.FragmentStoreHomeBinding
import com.example.todayhome.src.main.store.DetailActivity
import com.example.todayhome.src.main.store.adapter.GridMenuStoreHomeAdapter
import com.example.todayhome.src.main.store.adapter.GridMenuStoreHomeAdapter2
import com.example.todayhome.src.main.store.adapter.ViewPage2StoreHomeAdapter
import com.example.todayhome.src.main.store.data.StoreHomeBannerItemList
import com.example.todayhome.src.main.store.data.StoreHomeGridItemList
import com.example.todayhome.src.main.store.data.StoreHomeGridItemList2
import com.example.todayhome.src.main.store.storeHomeViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class StoreHomeFragment : Fragment() {


    private var _binding: FragmentStoreHomeBinding? = null
    private val binding get() = _binding!!
    private var bannerPosition = 0
    lateinit var job: Job
    private lateinit var viewModel: storeHomeViewModel

    private val viewPagerAdapter = ViewPage2StoreHomeAdapter(itemClickedListener = {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("cookModel2", it)
        startActivity(intent)
    })

    private lateinit var storeservice: StoreService
    private lateinit var gridRecyclerViewAdapter: GridMenuStoreHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoreHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(storeHomeViewModel::class.java)
        viewModel.setBannerItems(StoreHomeBannerItemList)
        viewModel.setGridItems(StoreHomeGridItemList)
        viewModel.setGridItems2(StoreHomeGridItemList2)


        binding.viewPager2.adapter = viewPagerAdapter
        binding.viewPager2Indicator.attachTo(binding.viewPager2)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://prod.rc-rising-test-6th.shop/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        storeservice = retrofit.create(StoreService::class.java)
        storeservice.storeApi()
            .enqueue(object : Callback<storeAPI> {
                override fun onFailure(call: Call<storeAPI>, t: Throwable) {

                }

                override fun onResponse(call: Call<storeAPI>, response: Response<storeAPI>) {
                    if (response.isSuccessful.not()) {
                        return
                    }

                    response.body()?.let { it ->
//                        Log.d(HomeFragment.TAG, it.toString())
                        viewPagerAdapter.submitList(it.result?.eventImgs)
                    }

                }

            })



        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bannerPosition = position


            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                when (state) {
                    ViewPager2.SCROLL_STATE_IDLE ->{
                        if (!job.isActive) scrollJobCreate()
                    }

                    ViewPager2.SCROLL_STATE_DRAGGING -> job.cancel()

                    ViewPager2.SCROLL_STATE_SETTLING -> {}
                }
            }
        })
        binding.viewPager2.apply {
            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.viewPager2Indicator.attachTo(binding.viewPager2)

                }
            })

    }

        initViewPager2()
        subscribeObservers()
        return binding.root
    }


    private fun initViewPager2() {
        binding.menuRecyclerView.apply {
            gridRecyclerViewAdapter = GridMenuStoreHomeAdapter()
            layoutManager = GridLayoutManager(context, 5)
            adapter = gridRecyclerViewAdapter
        }

    }

    private fun subscribeObservers() {

        viewModel.gridItemList.observe(viewLifecycleOwner, Observer {gridItemList->
            gridRecyclerViewAdapter.submitList(gridItemList)
        })



    }



    fun scrollJobCreate() {
        job = lifecycleScope.launchWhenResumed {
            delay(1500)
            binding.viewPager2.setCurrentItem(++bannerPosition, true)
        }
    }
    override fun onResume() {
        super.onResume()
        scrollJobCreate()
    }

    override fun onPause() {
        super.onPause()
        job.cancel()
    }




}