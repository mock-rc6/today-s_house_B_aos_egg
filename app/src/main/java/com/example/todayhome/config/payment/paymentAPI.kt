package com.example.todayhome.config.payment


import com.google.gson.annotations.SerializedName

data class paymentAPI(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("isSuccess")
    val isSuccess: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("result")
    val result: Result?
)