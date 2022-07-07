package com.example.todayhome.config.Scap


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("categoryList")
    val categoryList: List<Any>?,
    @SerializedName("folderList")
    val folderList: List<Any>?,
    @SerializedName("houseScrapList")
    val houseScrapList: List<Any>?,
    @SerializedName("itemScrapList")
    val itemScrapList: List<Any>?,
    @SerializedName("profileThumbnail")
    val profileThumbnail: String?,
    @SerializedName("userId")
    val userId: Int?,
    @SerializedName("userName")
    val userName: String?
)