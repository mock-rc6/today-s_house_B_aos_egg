package com.example.todayhome.src.main.HomeFragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todayhome.BuildConfig
import com.example.todayhome.R
import com.example.todayhome.config.home.api.EventAPI
import com.example.todayhome.config.home.api.HomeService
import com.example.todayhome.databinding.ActivityEventBinding
import com.example.todayhome.databinding.ActivityMainBinding
import com.example.todayhome.src.main.HomeFragment.adapter.EventAdapter
import com.example.todayhome.src.main.store.DetailActivity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EventActivity : AppCompatActivity() {
    private val binding: ActivityEventBinding by lazy {
        ActivityEventBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: EventAdapter
    private lateinit var eventService: HomeService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initEventRecyclerView()

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
//                    DEBUG일때만 BODY까지지  보여줌
                    level =if(BuildConfig.DEBUG){
                        HttpLoggingInterceptor.Level.BODY
                    }else{
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            ).build()


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        eventService = retrofit.create(HomeService::class.java)
        eventService.event(getJwt().toString())
            .enqueue(object : Callback<EventAPI> {
                override fun onFailure(call: Call<EventAPI>, t: Throwable) {

                }

                override fun onResponse(call: Call<EventAPI>, response: Response<EventAPI>) {
                    if (response.isSuccessful.not()) {
                        return
                    }

                    response.body()?.let { it ->
//                        Log.d(HomeFragment.TAG, it.toString())
                        adapter.submitList(it.result)
                    }

                }

            })


    }

    private fun initEventRecyclerView() {
        adapter = EventAdapter()
        binding.eventRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.eventRecyclerView.adapter = adapter
    }

    private fun getJwt(): String? {
        val spf = this.getSharedPreferences("auth2", AppCompatActivity.MODE_PRIVATE)

        return spf!!.getString("jwt", "")
    }



    companion object {
        private const val BASE_URL = "https://prod.rc-rising-test-6th.shop/"
    }
}