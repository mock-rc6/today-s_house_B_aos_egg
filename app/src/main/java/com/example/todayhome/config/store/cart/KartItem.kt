package com.example.todayhome.config.store.cart


import com.google.gson.annotations.SerializedName

data class KartItem(
    @SerializedName("delivery")
    val delivery: String?,
    @SerializedName("imgUrl")
    val imgUrl: String?,
    @SerializedName("itemNum")
    val itemNum: Int?,
    @SerializedName("kartId")
    val kartId: Int?,
    @SerializedName("optionId")
    val optionId: Int?,
    @SerializedName("optionName")
    val optionName: String?,
    @SerializedName("price")
    val price: String?
)