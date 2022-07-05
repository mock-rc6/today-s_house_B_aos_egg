package com.example.todayhome.src.main.store


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.todayhome.BuildConfig
import com.example.todayhome.config.mypage.api.MyPageService
import com.example.todayhome.config.mypage.api.UserMyPage
import com.example.todayhome.config.store.itemdetail.ItemDetail
import com.example.todayhome.config.store.itemdetail.ItemDetailIInterface
import com.example.todayhome.databinding.ActivityDetailPopularBinding
import com.example.todayhome.src.main.store.adapter.ItemDetailAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailPopularActivity : AppCompatActivity() {
    private val binding: ActivityDetailPopularBinding by lazy {
        ActivityDetailPopularBinding.inflate(layoutInflater)
    }

    private lateinit var myPageService: ItemDetailIInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        val TodaysDeal = intent.getParcelableExtra<TodaysDeal>("bannerModel")


        tabLayoutViewPage2()

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

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        myPageService = retrofit.create(ItemDetailIInterface::class.java)
        myPageService.detail(getJwt().toString(),"1",getJwt2().toString())
            .enqueue(object : Callback<ItemDetail> {
                override fun onFailure(call: Call<ItemDetail>, t: Throwable) {

                }

                override fun onResponse(call: Call<ItemDetail>, response: Response<ItemDetail>) {
                    if (response.isSuccessful.not()) {
                        return
                    }

                    response.body()?.let { it ->
                        Log.d("코난디테일",it.toString())
                    }

                }

            })

        binding.homeTabLayoutViewPager2.currentItem = 1

    }


    private fun tabLayoutViewPage2() {
        val adapter = ItemDetailAdapter(this)
        binding.homeTabLayoutViewPager2.adapter = adapter

        val tabName = arrayOf("상품정보", "리뷰", "문의", "배송/환불", "추천")


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

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth2", AppCompatActivity.MODE_PRIVATE)

        return spf!!.getString("jwt", "")
    }

    private fun getJwt2(): Long {
        val spf = this.getSharedPreferences("auth3", AppCompatActivity.MODE_PRIVATE)

        return spf!!.getLong("jwt", 0)
    }

    companion object {
        private const val BASE_URL = "https://prod.rc-rising-test-6th.shop/"
    }

}



