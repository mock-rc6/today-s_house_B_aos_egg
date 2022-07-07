package com.example.todayhome.src.main.store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.todayhome.BuildConfig
import com.example.todayhome.R
import com.example.todayhome.config.store.api.StoreService
import com.example.todayhome.config.store.cart.CartLookInterface
import com.example.todayhome.config.store.cart.cartLook
import com.example.todayhome.config.store.storeAPI
import com.example.todayhome.databinding.ActivityCartConfirmBinding
import com.example.todayhome.databinding.ActivityMainBinding
import com.facebook.appevents.codeless.internal.ViewHierarchy.setOnClickListener
import okhttp3.OkHttpClient
import okhttp3.internal.filterList
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class cart_confirm_Activity : AppCompatActivity() {

    private lateinit var service: CartLookInterface

    private val binding: ActivityCartConfirmBinding by lazy {
        ActivityCartConfirmBinding.inflate(layoutInflater)
    }

    var emailArray: Array<String> = arrayOf(
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        "10"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.payButton.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }



        binding.ListButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setItems(
                    emailArray
                ) { _, which ->
                    binding.ListButton.text = emailArray[which]
                    binding.ListButton.text.toString().forEach {
                        it.lowercase()
                    }
                }
            builder.show()
        }

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
            .baseUrl("https://prod.rc-rising-test-6th.shop/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        service = retrofit.create(CartLookInterface::class.java)
        service.cart(getJwt().toString(), getJwt2())
            .enqueue(object : Callback<cartLook> {
                override fun onFailure(call: Call<cartLook>, t: Throwable) {

                }

                override fun onResponse(call: Call<cartLook>, response: Response<cartLook>) {
                    if (response.isSuccessful.not()) {
                        return
                    }

                    response.body()?.let { it ->

                        val img = ""
                        it.result?.kartItemList?.forEach {


                        }
                        Log.d("코난 함정수사", img.toString())


                        binding.totalAmount.text = it.result?.price.toString()
                        binding.totalSale.text = "-" + it.result?.discountPrice.toString()
                        binding.payment.text = it.result?.saledPrice.toString()
                        binding.payTextView.text = it.result?.saledPrice.toString()

                        it.result?.kartItemList?.forEach {
                            binding.cartTextView2.text = it.optionName.toString()
                            binding.ListButton.text = it.itemNum.toString()
                            binding.numberTextView.text = it.itemNum.toString() + "개"
                            binding.itemPriceTextView.text = it.price.toString()
                            binding.itemPriceTextView2.text = it.price.toString()


                            Glide
                                .with(binding.cartImageView.context)
                                .asBitmap()
                                .load(it.imgUrl)
                                .into(binding.cartImageView)
                        }


                    }

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
}