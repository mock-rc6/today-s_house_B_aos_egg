package com.example.todayhome.src.main.store.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.todayhome.R
import com.example.todayhome.config.store.api.StoreService
import com.example.todayhome.config.store.itemdetail.ItemDetail
import com.example.todayhome.config.store.itemdetail.ItemDetailIInterface
import com.example.todayhome.config.store.storeAPI
import com.example.todayhome.databinding.FragmentReviewBinding
import com.example.todayhome.src.main.store.adapter.ReviewViewPageAdapter
import com.example.todayhome.src.main.store.adapter.reviewListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ReviewFragment : Fragment() {

    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!
    private val viewPagerAdapter2 = ReviewViewPageAdapter()
    private lateinit var storeservice: ItemDetailIInterface
    private lateinit var popularAdapter: reviewListAdapter
    private lateinit var storeservice2: StoreService
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://prod.rc-rising-test-6th.shop/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        storeservice = retrofit.create(ItemDetailIInterface::class.java)
        storeservice.detail(getJwt().toString(), "1", getJwt2().toString())
            .enqueue(object : Callback<ItemDetail> {
                override fun onFailure(call: Call<ItemDetail>, t: Throwable) {

                }

                override fun onResponse(call: Call<ItemDetail>, response: Response<ItemDetail>) {
                    if (response.isSuccessful.not()) {
                        return
                    }

                    response.body()?.let { it ->
                        Log.d("코난", it.toString())
                        popularAdapter.submitList(it.result?.reviewList)
                        binding.reviewCount.text = "( " + it.result?.scrapCnt.toString() + " )"


                    }

                }

            })
        val retrofit2 = Retrofit.Builder()
            .baseUrl("https://prod.rc-rising-test-6th.shop/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        storeservice2 = retrofit2.create(StoreService::class.java)
        storeservice2.storeApi()
            .enqueue(object : Callback<storeAPI> {
                override fun onFailure(call: Call<storeAPI>, t: Throwable) {

                }

                override fun onResponse(call: Call<storeAPI>, response: Response<storeAPI>) {
                    if (response.isSuccessful.not()) {
                        return
                    }

                    response.body()?.let { it ->
                        Log.d("코난", it.result?.todaysDealList.toString())
                        viewPagerAdapter2.submitList(it.result?.todaysDealList)
                    }

                }

            })

        _binding = FragmentReviewBinding.inflate(inflater, container, false)
        viewPage2side()
        initPopularRecyclerView()
        return binding.root
    }

    private fun initPopularRecyclerView() {
        popularAdapter = reviewListAdapter()

        binding.popularItemRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.popularItemRecyclerView.adapter = popularAdapter


    }

    // 오늘의 딜 오른쪽 보이게 하기
    private fun viewPage2side() {
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin3)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth3)
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

    private fun getJwt(): String? {
        val spf = activity?.getSharedPreferences("auth2", AppCompatActivity.MODE_PRIVATE)

        return spf!!.getString("jwt", "")
    }

    private fun getJwt2(): Long {
        val spf = activity?.getSharedPreferences("auth3", AppCompatActivity.MODE_PRIVATE)

        return spf!!.getLong("jwt", 0)
    }


}