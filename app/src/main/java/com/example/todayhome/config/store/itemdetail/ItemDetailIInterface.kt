package com.example.todayhome.config.store.itemdetail


import retrofit2.Call
import retrofit2.http.*

interface ItemDetailIInterface {

    @GET("/app/store/items")
    fun detail(
        @Header("X-ACCESS-TOKEN") JWT: String,
        @Query("id") id: String,
        @Query("user") user:String
    ): Call<ItemDetail>
}