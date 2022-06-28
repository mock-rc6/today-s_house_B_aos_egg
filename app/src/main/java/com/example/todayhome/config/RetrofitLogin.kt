package com.example.todayhome.config

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitLogin {
    @POST("/users")
    fun signUp(@Body user: User): Call<ResponseLogin>

    @POST("/users/log-in")
    fun login(@Body user: User): Call<ResponseLogin>

}