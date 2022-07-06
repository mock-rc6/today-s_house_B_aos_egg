package com.example.todayhome.config.store.itemCart


import retrofit2.Call
import retrofit2.http.*

interface ItemCartInterface {

    @POST("app/store/{userId}/items")
    fun itemCart(
        @Header("X-ACCESS-TOKEN") JWT: String,
        @Body cartBody: CartBody,
        @Path("userId") userId: Long,
        @Query("id") id: Long
        ): Call<itemCartAPI>
}