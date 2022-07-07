package com.example.todayhome.config.store.cart


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("delivery")
    val delivery: String?,
    @SerializedName("discountPrice")
    val discountPrice: String?,
    @SerializedName("itemNum")
    val itemNum: String?,
    @SerializedName("kartItemList")
    val kartItemList: List<KartItem>?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("saledPrice")
    val saledPrice: String?
)