package com.example.todayhome.config.KaKaoLogin


import com.google.gson.annotations.SerializedName

data class kakaoLogin(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("isSuccess")
    val isSuccess: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("result")
    val result: Result?
)