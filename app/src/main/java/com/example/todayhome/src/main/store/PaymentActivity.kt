package com.example.todayhome.src.main.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.todayhome.BuildConfig
import com.example.todayhome.config.pay
import com.example.todayhome.config.payment.payService
import com.example.todayhome.config.payment.paymentAPI
import com.example.todayhome.config.store.Scrap.scrapInterface
import com.example.todayhome.config.store.itemdetail.ItemDetail
import com.example.todayhome.config.store.itemdetail.ItemDetailIInterface
import com.example.todayhome.databinding.ActivityCartConfirmBinding
import com.example.todayhome.databinding.ActivityPaymentBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PaymentActivity : AppCompatActivity() {

    private val binding: ActivityPaymentBinding by lazy {
        ActivityPaymentBinding.inflate(layoutInflater)
    }

    private lateinit var payservice: payService

    var emailArray: Array<String> = arrayOf(
        "naver.com",
        "hanmail.net",
        "daum.net",
        "gamil.com",
        "kakao.com",
        "nate.com",
        "hotmail.com",
        "outlook.com",
        "icloud.com",
        "직접입력"
    )
    var phoneArray: Array<String> = arrayOf(
        "010",
        "011",
        "016",
        "017",
        "018",
        "019"
    )

    var delivery: Array<String> = arrayOf(
        "배송시 요청사항을 선택해주세요",
        "부재시 문앞에 놓아주세요",
        "배송전에 미리 연락주세요",
        "부재시 경비실에 맡겨 주세요",
        "부재시 전화주시거나 문자 남겨 주세요",
        "직접입력"
    )

    var card: Array<String> = arrayOf(
        "우리카드",
        "KB국민카드",
        "현대카드",
        "롯데카드",
        "신한카드",
        "씨티카드",
        "비씨카드",
        "삼성카드",
        "하나카드",
        "NH농협카드",
        "카카오뱅크카드"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.emailListButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setItems(
                    emailArray
                ) { _, which ->
                    binding.emailListButton.text = emailArray[which]
                    binding.emailListButton.text.toString().forEach {

                    }
                }
            builder.show()

        }

        binding.phoneListButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setItems(
                    phoneArray
                ) { _, which ->
                    binding.phoneListButton.text = phoneArray[which]
                    binding.phoneListButton.text.toString().forEach {

                    }
                }
            builder.show()

        }

        binding.receivePhoneListButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setItems(
                    phoneArray
                ) { _, which ->
                    binding.receivePhoneListButton.text = phoneArray[which]
                    binding.receivePhoneListButton.text.toString().forEach {

                    }
                }
            builder.show()

        }

        binding.RequestsButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setItems(
                    delivery
                ) { _, which ->
                    binding.RequestsButton.text = delivery[which]
                    binding.RequestsButton.text.toString().forEach {

                    }
                }
            builder.show()

        }

        binding.cardListButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setItems(
                    card
                ) { _, which ->
                    binding.cardListButton.text = card[which]
                    binding.cardListButton.text.toString().forEach {

                    }
                }
            builder.show()

        }
        binding.payMentButton.setOnClickListener {
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

            payservice = retrofit.create(payService::class.java)
            payservice.pay(getJwt().toString(), getUser(), getJwt2())
                .enqueue(object : Callback<paymentAPI> {
                    override fun onFailure(call: Call<paymentAPI>, t: Throwable) {
                        Log.d("코난 추리 실패", t.message.toString())
                    }

                    override fun onResponse(
                        call: Call<paymentAPI>,
                        response: Response<paymentAPI>
                    ) {
                        val resp: paymentAPI = response.body()!!
                        Log.d("코난 추리 성공1", resp.message.toString())
                        Log.d("코난 추리 성공2", resp.toString())
                        Log.d("코난 추리 성공3", getUser().toString())

                        when (resp.code) {
                            1000 -> finish()
                        }

                        response.body()?.let { it ->
                            Log.d("코난", it.toString())
                            binding.itemNameTextView.text= it.result?.orderedItem.toString()
                            binding.totalItemAmount2.text =it.result?.price.toString()
                            binding.itemPaymentPriceTextView.text =it.result?.price.toString()
                            binding.finalAmount2.text =it.result?.price.toString()
                            binding.bottomPatment.text =it.result?.price.toString()


                        }


                    }

                })


        }
    }


    private fun getUser(): pay {
        val list1: List<Long> = listOf(43)
        val orderName: String = binding.editName.text.toString()
        val phoneNum: String =
            binding.phoneListButton.text.toString() + binding.editPhone.text.toString()
        val email: String =
            binding.editEmail.text.toString() + "@" + binding.emailListButton.text.toString()
        val receivedName: String = binding.editReceiveName.text.toString()
        val receivedPhone: String =
            binding.receivePhoneListButton.text.toString() + binding.editPhoneListButton.text.toString()
        val placeName: String = binding.editAddressName.text.toString()
        val addressCode: String = binding.editHomeAdress.text.toString()
        val address: String =
            binding.editHomeAdress2.text.toString() + binding.editHomeAdress3.text.toString()


        return pay(
            7,
            list1,
            0,
            0,
            orderName,
            phoneNum,
            email,
            receivedName,
            receivedPhone,
            placeName,
            addressCode,
            address
        )
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



