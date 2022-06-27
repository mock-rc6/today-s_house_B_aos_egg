package com.example.todayhome.config

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SignUpInterface {

    @FormUrlEncoded
    @POST("/users")
    fun login(): Call<ResponseBody>
}