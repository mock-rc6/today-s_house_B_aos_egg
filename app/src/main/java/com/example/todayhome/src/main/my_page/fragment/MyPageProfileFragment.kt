package com.example.todayhome.src.main.my_page.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.todayhome.BuildConfig
import com.example.todayhome.config.mypage.api.UserMyPage
import com.example.todayhome.config.mypage.api.MyPageService
import com.example.todayhome.databinding.FragmentMyPageProfileBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyPageProfileFragment : Fragment() {

    private var _binding: FragmentMyPageProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var myPageService: MyPageService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageProfileBinding.inflate(inflater, container, false)


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

        myPageService = retrofit.create(MyPageService::class.java)
        myPageService.userMyPage(getJwt().toString(),getJwt2())
            .enqueue(object : Callback<UserMyPage> {
                override fun onFailure(call: Call<UserMyPage>, t: Throwable) {

                }

                override fun onResponse(call: Call<UserMyPage>, response: Response<UserMyPage>) {
                    if (response.isSuccessful.not()) {
                        return
                    }

                    response.body()?.let { it ->
                        binding.nameTextView.text = it.result?.name.toString()
                        binding.followsTextView.text = it.result?.follows.toString()
                        binding.followersTextView.text = it.result?.followers.toString()
                        binding.likeNumberTextView.text = it.result?.likes.toString()
                        binding.scrapNumberTextView.text = it.result?.scraps.toString()
                        binding.orderTextView.text = it.result?.orderHistory.toString()
                        binding.myCouponTextView.text = it.result?.coupons.toString()
                        binding.myPointTextView.text = it.result?.points.toString()
                        binding.QnANumberTextView.text=it.result?.inquiry.toString()
                        binding.reviewNumberTextView.text=it.result?.myReviews.toString()

                        Glide
                            .with(binding.profilePicImageView.context)
                            .asBitmap()
                            .load(it.result?.profilePic)
                            .into(binding.profilePicImageView)




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