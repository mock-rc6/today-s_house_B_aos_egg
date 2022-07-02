package com.example.todayhome.config.home.api


import com.google.gson.annotations.SerializedName

data class EventAPI(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("isSuccess")
    val isSuccess: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("result")
    val result: List<Result>?
)