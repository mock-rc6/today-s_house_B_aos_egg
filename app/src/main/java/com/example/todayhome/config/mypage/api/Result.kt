package com.example.todayhome.config.mypage.api


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("coupons")
    val coupons: Int?,
    @SerializedName("followers")
    val followers: Int?,
    @SerializedName("follows")
    val follows: Int?,
    @SerializedName("inquiry")
    val inquiry: Int?,
    @SerializedName("likes")
    val likes: Int?,
    @SerializedName("myReviews")
    val myReviews: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("orderHistory")
    val orderHistory: Int?,
    @SerializedName("points")
    val points: Int?,
    @SerializedName("profilePic")
    val profilePic: String?,
    @SerializedName("scraps")
    val scraps: Int?
)