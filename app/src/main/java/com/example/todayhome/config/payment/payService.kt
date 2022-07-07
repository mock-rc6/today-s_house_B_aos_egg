package com.example.todayhome.config.payment



import com.example.todayhome.config.pay
import retrofit2.Call
import retrofit2.http.*

interface payService {

    @POST("/users/payments/{userId}")
    fun pay(
        @Header("X-ACCESS-TOKEN") JWT: String,
        @Body pay: pay,
        @Path("userId") userId: Long,
    ): Call<paymentAPI>

}