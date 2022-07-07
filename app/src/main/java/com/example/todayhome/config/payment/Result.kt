package com.example.todayhome.config.payment


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("count")
    val count: String?,
    @SerializedName("orderThumbnail")
    val orderThumbnail: String?,
    @SerializedName("orderedItem")
    val orderedItem: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("receiptId")
    val receiptId: Int?
)