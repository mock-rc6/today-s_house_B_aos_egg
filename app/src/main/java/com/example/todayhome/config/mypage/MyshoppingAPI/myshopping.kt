package com.example.todayhome.config.mypage.MyshoppingAPI


import com.google.gson.annotations.SerializedName

data class myshopping(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("isSuccess")
    val isSuccess: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("result")
    val result: Result?
)