package com.example.todayhome.config.bannerClickListener


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("bannerPic")
    val bannerPic: String?,
    @SerializedName("due")
    val due: String?,
    @SerializedName("eventId")
    val eventId: Int?
)