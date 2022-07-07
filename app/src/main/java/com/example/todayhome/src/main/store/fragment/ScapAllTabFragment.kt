package com.example.todayhome.src.main.store.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.todayhome.R
import com.example.todayhome.config.store.api.StoreService
import com.example.todayhome.config.store.storeAPI
import com.example.todayhome.databinding.FragmentStoreHomeBinding
import com.example.todayhome.src.main.store.DetailPopularActivity
import com.example.todayhome.src.main.store.adapter.PopularItemAdapter
import com.example.todayhome.src.main.store.adapter.ScapItemAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ScapAllTabFragment : Fragment() {
    private var _binding: FragmentStoreHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var popularAdapter: ScapItemAdapter
    private lateinit var storeservice: StoreService
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoreHomeBinding.inflate(inflater, container, false)

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

                        popularAdapter.submitList(it.result?.todaysDealList)

                    }

                }

            })



        initPopularRecyclerView()
        return binding.root
    }

    private fun initPopularRecyclerView() {
        popularAdapter = ScapItemAdapter()

        binding.popularItemRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.popularItemRecyclerView.adapter = popularAdapter
    }


}