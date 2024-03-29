package com.example.todayhome.config.store.itemdetail


import com.google.gson.annotations.SerializedName

data class ItemDetail(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("isSuccess")
    val isSuccess: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("result")
    val result: Result?
)