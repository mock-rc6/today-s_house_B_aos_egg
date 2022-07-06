package com.example.todayhome.config.store.itemCart


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("kartId")
    val kartId: Int?,
    @SerializedName("message")
    val message: String?
)