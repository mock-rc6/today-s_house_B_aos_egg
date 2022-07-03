package com.example.todayhome.config.mypage.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MyPageService {

    @GET("/app/{userId}")
    fun userMyPage(
        @Header("X-ACCESS-TOKEN") JWT: String,
        @Path("userId") userId: Long
    ): Call<UserMyPage>
}