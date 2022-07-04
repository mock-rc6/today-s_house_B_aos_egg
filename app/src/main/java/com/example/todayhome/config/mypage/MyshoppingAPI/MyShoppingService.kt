package com.example.todayhome.config.mypage.MyshoppingAPI


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MyShoppingService {

    @GET("/app/{userId}/my-shoppings")
    fun myShopping(
        @Header("X-ACCESS-TOKEN") JWT: String,
        @Path("userId") userId: Long
    ): Call<myshopping>
}
