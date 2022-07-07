package com.example.todayhome.src.main.store


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.todayhome.BuildConfig
import com.example.todayhome.R
import com.example.todayhome.config.store.Scrap.scrapAPI
import com.example.todayhome.config.store.Scrap.scrapInterface
import com.example.todayhome.config.store.itemCart.CartBody
import com.example.todayhome.config.store.itemCart.ItemCartInterface
import com.example.todayhome.config.store.itemCart.itemCartAPI
import com.example.todayhome.config.store.itemdetail.ItemDetail
import com.example.todayhome.config.store.itemdetail.ItemDetailIInterface
import com.example.todayhome.databinding.ActivityDetailPopularBinding
import com.example.todayhome.src.main.store.adapter.ItemDetailAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
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
    var number: String = "0"

    private lateinit var myPageService: ItemDetailIInterface
    private lateinit var scrap: scrapInterface
    private lateinit var cart: ItemCartInterface


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bottomSheetView = layoutInflater.inflate(R.layout.activity_scrap_dialog, null)
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetView)
        var scapLook = bottomSheetDialog.findViewById<ImageButton>(R.id.scapLook)


        val bottomBuySheetView = layoutInflater.inflate(R.layout.activity_buy, null)
        val bottomSheetDialog2 = BottomSheetDialog(this)
        bottomSheetDialog2.setContentView(bottomBuySheetView)

        val bottomCartSheetView = layoutInflater.inflate(R.layout.activity_cart, null)
        val bottomSheetDialog3 = BottomSheetDialog(this)
        bottomSheetDialog3.setContentView(bottomCartSheetView)

        val plus = bottomSheetDialog2.findViewById<ImageButton>(R.id.plus)
        val minus = bottomSheetDialog2.findViewById<ImageButton>(R.id.minus)
        val cartButton2 = bottomSheetDialog2.findViewById<ImageButton>(R.id.cartButton2)
        val buyButton = bottomSheetDialog2.findViewById<ImageButton>(R.id.buyButton)

        var itemMoney = bottomSheetDialog2.findViewById<TextView>(R.id.itemMoney)
        var itemprice = bottomSheetDialog2.findViewById<TextView>(R.id.itemprice)
        var itemCount = bottomSheetDialog2.findViewById<TextView>(R.id.itemCount)


        var shortcutButton = bottomSheetDialog3.findViewById<ImageButton>(R.id.shortcutButton)

        shortcutButton!!.setOnClickListener {
            val intent = Intent(this, cart_confirm_Activity::class.java)
            startActivity(intent)

        }

        scapLook!!.setOnClickListener {
            val intent = Intent(this, scapbookActivity::class.java)
            startActivity(intent)

        }


        binding.payButton.setOnClickListener {
            bottomSheetDialog2.show()
        }

        plus!!.setOnClickListener {
            number = (number.toInt() + 1).toString()
            itemCount!!.text = number

        }

        minus!!.setOnClickListener {
            if (number.toInt() > 0) {
                number = (number.toInt() - 1).toString()
                itemCount!!.text = number
            }

        }

        fun getUser(): CartBody {
            val optionId: Long = 1
            number = itemCount!!.text.toString()


            return CartBody(optionId, number.toInt())
        }

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
        myPageService.detail(getJwt().toString(), "1", getJwt2().toString())
            .enqueue(object : Callback<ItemDetail> {
                override fun onFailure(call: Call<ItemDetail>, t: Throwable) {

                }

                override fun onResponse(call: Call<ItemDetail>, response: Response<ItemDetail>) {
                    if (response.isSuccessful.not()) {
                        return
                    }

                    response.body()?.let { it ->
                        Log.d("코난디테일", it.toString())
                        binding.itemName.text = it.result?.itemName.toString()
                        binding.itemName2.text = it.result?.itemName.toString()
                        binding.brand.text = it.result?.companyName.toString()
                        binding.starNum.text = "( " + it.result?.reviewCnt.toString() + " )"
                        binding.discount.text = it.result?.saleRate.toString()
                        binding.price.text = it.result?.price.toString()
                        binding.brandTextName.text = it.result?.companyName.toString()
                        binding.scrapText.text = it.result?.scrapCnt.toString()
                        Glide
                            .with(binding.coverImageView.context)
                            .asBitmap()
                            .load(it.result?.imgList?.get(0))
                            .into(binding.coverImageView)

                    }

                }

            })

        val retrofit2 = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        scrap = retrofit2.create(scrapInterface::class.java)
        scrap.scrap(getJwt().toString(), 1, getJwt2())
            .enqueue(object : Callback<scrapAPI> {
                override fun onFailure(call: Call<scrapAPI>, t: Throwable) {

                }

                override fun onResponse(call: Call<scrapAPI>, response: Response<scrapAPI>) {
                    val resp: scrapAPI = response.body()!!
                    binding.scrapButton.setOnClickListener {
                        binding.scrapButton.setBackgroundResource(R.drawable.scrapon)
                        when (resp.code) {
                            1000 -> bottomSheetDialog.show()
                        }
                    }
                }

            })

        binding.homeTabLayoutViewPager2.currentItem = 1

        cartButton2!!.setOnClickListener {
            Log.d("코난탐정", getUser().toString())

            val client2: OkHttpClient = OkHttpClient.Builder()
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

            val retrofit2 = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client2)
                .build()

            cart = retrofit2.create(ItemCartInterface::class.java)
            cart.itemCart(getJwt().toString(), getUser(), getJwt2(), 1)
                .enqueue(object : Callback<itemCartAPI> {
                    override fun onFailure(call: Call<itemCartAPI>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<itemCartAPI>,
                        response: Response<itemCartAPI>
                    ) {
                        if (response.isSuccessful.not()) {
                            return
                        }
                        val resp: itemCartAPI = response.body()!!
                        response.body()?.let { it ->
                            Log.d("코난장바구니", it.toString())

                            when (resp.code) {
                                1000,2038 -> {
                                    bottomSheetDialog2.cancel()
                                    bottomSheetDialog3.show()
                                }
                            }


                        }

                    }

                })
        }






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



