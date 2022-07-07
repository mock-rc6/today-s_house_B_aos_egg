package com.example.todayhome.config.KaKaoLogin


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("jwt")
    val jwt: String?,
    @SerializedName("userId")
    val userId: Long?
)