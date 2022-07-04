package com.example.todayhome.config.mypage.MyshoppingAPI


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("bought")
    val bought: Int?,
    @SerializedName("coupons")
    val coupons: Int?,
    @SerializedName("delivery")
    val delivery: Int?,
    @SerializedName("finish")
    val finish: Int?,
    @SerializedName("inquiry")
    val inquiry: Int?,
    @SerializedName("level")
    val level: String?,
    @SerializedName("paid")
    val paid: Int?,
    @SerializedName("points")
    val points: Int?,
    @SerializedName("ready")
    val ready: Int?,
    @SerializedName("review")
    val review: Int?,
    @SerializedName("reviewWritten")
    val reviewWritten: Int?,
    @SerializedName("scraps")
    val scraps: Int?,
    @SerializedName("waiting")
    val waiting: Int?
)