package com.example.todayhome.config.store.api

import com.example.todayhome.config.ResponseLogin
import com.example.todayhome.config.User
import com.example.todayhome.config.store.storeAPI
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface StoreService {

    @GET("/app/store")
    fun storeApi(): Call<storeAPI>

}