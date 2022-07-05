package com.example.todayhome.config.store.itemdetail


import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("buyAt")
    val buyAt: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("imgList")
    val imgList: List<String>?,
    @SerializedName("itemName")
    val itemName: String?,
    @SerializedName("profilePic")
    val profilePic: String?,
    @SerializedName("score")
    val score: Double?,
    @SerializedName("userId")
    val userId: Int?,
    @SerializedName("userName")
    val userName: String?
)