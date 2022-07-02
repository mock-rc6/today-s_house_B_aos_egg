package com.example.todayhome.src.main.HomeFragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.todayhome.BuildConfig
import com.example.todayhome.R
import com.example.todayhome.config.home.api.EventAPI
import com.example.todayhome.config.home.api.HomeService
import com.example.todayhome.databinding.*
import com.example.todayhome.src.login.Password_reset_Activity
import com.example.todayhome.src.main.HomeFragment.adapter.BannerAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.absoluteValue


class PopularFragment : Fragment() {
    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!

    private val viewPagerAdapter = BannerAdapter()

    private lateinit var homeService: HomeService

    private var bannerPosition = 0
    lateinit var job: Job

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        binding.viewPager2.adapter = viewPagerAdapter

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
//                    DEBUG일때만 BODY까지지  보여줌
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            ).build()

        binding.bannerAddButton.setOnClickListener {
            val intent = Intent(activity, EventActivity::class.java)
            startActivity(intent)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        homeService = retrofit.create(HomeService::class.java)
        homeService.event(getJwt().toString())
            .enqueue(object : Callback<EventAPI> {
                override fun onFailure(call: Call<EventAPI>, t: Throwable) {

                }

                override fun onResponse(call: Call<EventAPI>, response: Response<EventAPI>) {
                    if (response.isSuccessful.not()) {
                        return
                    }

                    response.body()?.let { it ->
//                        Log.d(HomeFragment.TAG, it.toString())
                        viewPagerAdapter.submitList(it.result)
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

//        viewPage2side()
        binding.viewPager2.adapter = viewPagerAdapter
        binding.viewPager2.setCurrentItem(viewPagerAdapter.itemCount/2,false)


        binding.viewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bannerStartTextView.text = "${position%4+1}"
            }
        })


        return binding.root
    }


    companion object {
        private const val BASE_URL = "https://prod.rc-rising-test-6th.shop/"
    }

    private fun getJwt(): String? {
        val spf = activity?.getSharedPreferences("auth2", AppCompatActivity.MODE_PRIVATE)

        return spf!!.getString("jwt", "")
    }

    // 상당 뷰페이저 1초마다 이동
    fun scrollJobCreate() {
        job = lifecycleScope.launchWhenResumed {
            delay(2500)
            binding.viewPager2.setCurrentItem(++bannerPosition, true)
        }
    }

//    // 오늘의 딜 오른쪽 보이게 하기
//    private fun viewPage2side() {
//        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin2)
//        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pageWidth2)
//        val screenWidth = resources.displayMetrics.widthPixels
//        val offsetPx = screenWidth - pageMarginPx - pagerWidth
//
//        binding.viewPager2.setPageTransformer { page, position ->
//            page.translationX = position * -offsetPx
//        }
//
//        binding.viewPager2.offscreenPageLimit = 2
//        binding.viewPager2.adapter = viewPagerAdapter
//        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//
//
//
//    }



    override fun onResume() {
        super.onResume()
        scrollJobCreate()
    }

    override fun onPause() {
        super.onPause()
        job.cancel()
    }


}