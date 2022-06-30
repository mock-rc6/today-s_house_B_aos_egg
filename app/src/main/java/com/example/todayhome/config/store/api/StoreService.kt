package com.example.todayhome.config.store.api

import com.example.todayhome.config.store.storeAPI
import retrofit2.Call
import retrofit2.http.GET

interface StoreService {

    @GET("/app/store")
    fun storeApi(): Call<storeAPI>

}