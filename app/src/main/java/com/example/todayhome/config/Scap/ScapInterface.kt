package com.example.todayhome.config.Scap

import com.example.todayhome.config.mypage.api.UserMyPage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ScapInterface{

    @GET("/users/scraps/{userId}")
    fun userMyPage(
        @Header("X-ACCESS-TOKEN") JWT: String,
        @Path("userId") userId: Long
    ): Call<UserMyPage>
}