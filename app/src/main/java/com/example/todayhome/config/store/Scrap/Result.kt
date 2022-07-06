package com.example.todayhome.config.store.Scrap


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("message")
    val message: String?,
    @SerializedName("scrapId")
    val scrapId: Int?
)