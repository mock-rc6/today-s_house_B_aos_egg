package com.example.todayhome.config.store.itemdetail


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("companyId")
    val companyId: Int?,
    @SerializedName("companyName")
    val companyName: String?,
    @SerializedName("five")
    val five: Int?,
    @SerializedName("four")
    val four: Int?,
    @SerializedName("imgList")
    val imgList: List<String>?,
    @SerializedName("inquiry")
    val inquiry: Int?,
    @SerializedName("itemInfoList")
    val itemInfoList: List<String>?,
    @SerializedName("itemName")
    val itemName: String?,
    @SerializedName("one")
    val one: Int?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("reviewCnt")
    val reviewCnt: Int?,
    @SerializedName("reviewList")
    val reviewList: List<Review>?,
    @SerializedName("saleRate")
    val saleRate: String?,
    @SerializedName("score")
    val score: Double?,
    @SerializedName("scrap")
    val scrap: Boolean?,
    @SerializedName("scrapCnt")
    val scrapCnt: Int?,
    @SerializedName("three")
    val three: Int?,
    @SerializedName("two")
    val two: Int?
)