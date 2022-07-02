package com.example.todayhome.config.home.api


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface HomeService {

    @GET("/app/events")
    fun event( @Header("X-ACCESS-TOKEN") JWT: String): Call<EventAPI>

}