package com.example.todayhome.config.store.cart


import com.example.todayhome.config.store.itemCart.CartBody
import com.example.todayhome.config.store.itemCart.itemCartAPI
import retrofit2.Call
import retrofit2.http.*

interface CartLookInterface {
    @GET("/users/karts/{userId}")
    fun cart(
        @Header("X-ACCESS-TOKEN") JWT: String,
        @Path("userId") userId: Long,
        ): Call<cartLook>
}