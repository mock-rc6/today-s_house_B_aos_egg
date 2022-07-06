package com.example.todayhome.config.store.Scrap

import com.example.todayhome.config.store.itemdetail.ItemDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface scrapInterface {

    @GET("/app/store/items")
    fun scrap(
        @Header("X-ACCESS-TOKEN") JWT: String,
        @Query("id") id: Long,
        @Query("user") user:Long
    ): Call<scrapAPI>
}