package com.example.todayhome.src.main.my_page.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.todayhome.BuildConfig
import com.example.todayhome.R
import com.example.todayhome.config.mypage.MyshoppingAPI.MyShoppingService
import com.example.todayhome.config.mypage.MyshoppingAPI.myshopping
import com.example.todayhome.config.mypage.api.MyPageService
import com.example.todayhome.config.mypage.api.UserMyPage
import com.example.todayhome.databinding.FragmentMyPageMyShoppingBinding
import com.example.todayhome.databinding.FragmentMyPageProfileBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyPageMyShoppingFragment : Fragment() {

    private var _binding: FragmentMyPageMyShoppingBinding? = null
    private val binding get() = _binding!!
    private lateinit var myShoppingService: MyShoppingService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMyPageMyShoppingBinding.inflate(inflater, container, false)

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

        myShoppingService = retrofit.create(MyShoppingService::class.java)
        myShoppingService.myShopping(getJwt().toString(),getJwt2())
            .enqueue(object : Callback<myshopping> {
                override fun onFailure(call: Call<myshopping>, t: Throwable) {

                }

                override fun onResponse(call: Call<myshopping>, response: Response<myshopping>) {
                    if (response.isSuccessful.not()) {
                        return
                    }

                    response.body()?.let { it ->
                        binding.couponNumberTextView.text = it.result?.coupons.toString()
                        binding.pointNumberTextView.text = it.result?.points.toString()
                        binding.RatingNumberTextView.text = it.result?.level.toString()
                        binding.waitTextView.text = it.result?.waiting.toString()
                        binding.paymentTextView.text = it.result?.paid.toString()
                        binding.ReadyTextView.text = it.result?.ready.toString()
                        binding.shippingTextView.text = it.result?.delivery.toString()
                        binding.completionTextView.text=it.result?.finish.toString()
                        binding.reviewTextView.text=it.result?.reviewWritten.toString()
                        binding.oderNumberTextView.text=it.result?.bought.toString()
                        binding.myReviewNumberTextView.text=it.result?.review.toString()
                        binding.reviewNumberTextView.text=it.result?.inquiry.toString()
                        binding.scrapbookNumberTextView.text=it.result?.scraps.toString()

                        Log.d("코난추리",it.toString())

                    }
                }
            })

        return binding.root
    }

    private fun getJwt(): String? {
        val spf = activity?.getSharedPreferences("auth2", AppCompatActivity.MODE_PRIVATE)

        return spf!!.getString("jwt", "")
    }

    private fun getJwt2(): Long {
        val spf = activity?.getSharedPreferences("auth3" , AppCompatActivity.MODE_PRIVATE)

        return spf!!.getLong("jwt", 0)
    }


    companion object {
        private const val BASE_URL = "https://prod.rc-rising-test-6th.shop/"
    }


}