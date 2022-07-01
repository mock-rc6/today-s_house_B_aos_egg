package com.example.todayhome.config

import retrofit2.Call
import retrofit2.http.*

interface RetrofitLogin {
    @POST("/users")
    fun signUp(@Body user: User): Call<ResponseLogin>

    @POST("/users/log-in")
    fun login(@Body user: User): Call<ResponseLogin>


    @PATCH("/users/{userId}")
    fun change(@Path("userId")userId:Long,@Body user: UserPassword) : Call<ResponseLogin2>

}