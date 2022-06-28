package com.example.todayhome.config.bannerClickListener


import com.google.gson.annotations.SerializedName

data class banner(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("isSuccess")
    val isSuccess: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("result")
    val result: List<Result>?
)