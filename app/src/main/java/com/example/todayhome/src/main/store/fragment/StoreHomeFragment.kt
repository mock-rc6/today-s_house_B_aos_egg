package com.example.todayhome.src.main.store.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.todayhome.R
import com.example.todayhome.config.store.api.StoreService
import com.example.todayhome.config.store.storeAPI
import com.example.todayhome.databinding.FragmentStoreHomeBinding
import com.example.todayhome.src.main.MainActivity
import com.example.todayhome.src.main.store.*

import com.example.todayhome.src.main.store.adapter.*
import com.example.todayhome.src.main.store.data.*
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
        intent.putExtra("bannerModel", it)
        startActivity(intent)
    })

    private val viewPagerAdapter2 = ViewPager2TodayDillAdapter(itemClickedListener = {
        val intent = Intent(activity, StoreHomeTodayDillActivity::class.java)
        intent.putExtra("todayModel", it)
        startActivity(intent)
    })


    private lateinit var storeservice: StoreService
    private lateinit var gridRecyclerViewAdapter: GridMenuStoreHomeAdapter
    private lateinit var gridRecyclerViewAdapter2: GridMenuStoreHomeAdapter2
    private lateinit var gridRecyclerViewAdapter3: PopularSearchAdapter

    private lateinit var popularAdapter: PopularItemAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoreHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[storeHomeViewModel::class.java]
        viewModel.setBannerItems(StoreHomeBannerItemList)
        viewModel.setGridItems(StoreHomeGridItemList)
        viewModel.setGridItems2(StoreHomeGridItemList2)
        viewModel.setGridItems3(PopularGridItemList)

        binding.viewPager2.adapter = viewPagerAdapter
        binding.viewPager2Indicator.attachTo(binding.viewPager2)

        binding.toDayDillViewPage2.adapter = viewPagerAdapter2
//        binding.recentItemViewPage2.adapter = viewPagerAdapter2
//        binding.RelationItemViewPage2.adapter = viewPagerAdapter2
//        binding.myItemViewPage2.adapter = viewPagerAdapter2
        initPopularRecyclerView()



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
                        Log.d("코난", it.result?.todaysDealList.toString())
                        viewPagerAdapter.submitList(it.result?.eventImgs)
                        viewPagerAdapter2.submitList(it.result?.todaysDealList)
                        popularAdapter.submitList(it.result?.todaysDealList)

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
                    ViewPager2.SCROLL_STATE_IDLE -> {
                        if (!job.isActive) scrollJobCreate()
                    }

                    ViewPager2.SCROLL_STATE_DRAGGING -> job.cancel()

                    ViewPager2.SCROLL_STATE_SETTLING -> {}
                }
            }
        })
        binding.viewPager2.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.viewPager2Indicator.attachTo(binding.viewPager2)

                }
            })

        }


        initViewPager2()
        subscribeObservers()
        viewPage2side()
        return binding.root
    }

    // Grid
    private fun initViewPager2() {
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

//        binding.popularRecyclerView.apply {
//            gridRecyclerViewAdapter3 = PopularSearchAdapter()
//            layoutManager = GridLayoutManager(context, 2)
//            adapter = gridRecyclerViewAdapter3
//        }

    }

    // Grid
    private fun subscribeObservers() {

        viewModel.gridItemList.observe(viewLifecycleOwner, Observer { gridItemList ->
            gridRecyclerViewAdapter.submitList(gridItemList)
        })

        viewModel.gridItemList2.observe(viewLifecycleOwner, Observer { gridItemList2 ->
            gridRecyclerViewAdapter2.submitList(gridItemList2)
        })
//        viewModel.gridItemList3.observe(viewLifecycleOwner, Observer { gridItemList3 ->
//            gridRecyclerViewAdapter3.submitList(gridItemList3)
//        })


    }

    // 오늘의 딜 오른쪽 보이게 하기
    private fun viewPage2side() {
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        binding.toDayDillViewPage2.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }

        binding.toDayDillViewPage2.offscreenPageLimit = 4
        binding.toDayDillViewPage2.adapter = viewPagerAdapter2
        binding.toDayDillViewPage2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

//        binding.recentItemViewPage2.setPageTransformer { page, position ->
//            page.translationX = position * -offsetPx
//        }
//
//        binding.recentItemViewPage2.offscreenPageLimit = 2
//        binding.recentItemViewPage2.adapter = viewPagerAdapter2
//        binding.recentItemViewPage2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//
//        binding.RelationItemViewPage2.setPageTransformer { page, position ->
//            page.translationX = position * -offsetPx
//        }
//
//        binding.RelationItemViewPage2.offscreenPageLimit = 2
//        binding.RelationItemViewPage2.adapter = viewPagerAdapter2
//        binding.RelationItemViewPage2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//
//        binding.myItemViewPage2.setPageTransformer { page, position ->
//            page.translationX = position * -offsetPx
//        }
//
//        binding.myItemViewPage2.offscreenPageLimit = 2
//        binding.myItemViewPage2.adapter = viewPagerAdapter2
//        binding.myItemViewPage2.orientation = ViewPager2.ORIENTATION_HORIZONTAL


    }

    private fun initPopularRecyclerView() {
        popularAdapter = PopularItemAdapter(itemClickedListener = {
            val intent = Intent(activity, DetailPopularActivity::class.java)
            intent.putExtra("popular", it)
            startActivity(intent)
        })

        binding.popularItemRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.popularItemRecyclerView.adapter = popularAdapter
    }


    // 상당 뷰페이저 1초마다 이동
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