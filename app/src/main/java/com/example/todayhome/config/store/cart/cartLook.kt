package com.example.todayhome.config.store.cart


import com.google.gson.annotations.SerializedName

data class cartLook(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("isSuccess")
    val isSuccess: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("result")
    val result: Result?
)