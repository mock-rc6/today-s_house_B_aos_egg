package com.example.todayhome.config.KaKaoLogin

import com.example.todayhome.config.home.api.EventAPI
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header

interface kakaoInterface {

    @GET("/oauth/authorize?client_id=f0510e9495bfefc35fcb0e04f193fdd8&redirect_uri=https://rc-rising-test-6th.shop/oauth/kakao&response_type=code")
    fun kakaoLogin( @Header("X-ACCESS-TOKEN") JWT: String): Call<kakaoLogin>
}