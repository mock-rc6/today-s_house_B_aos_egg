package com.example.todayhome.config.store


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TodaysDeal(
    @SerializedName("companyId")
    val companyId: Int?,
    @SerializedName("companyName")
    val companyName: String?,
    @SerializedName("due")
    val due: String?,
    @SerializedName("hotDealThumbnail")
    val hotDealThumbnail: String?,
    @SerializedName("itemId")
    val itemId: Int?,
    @SerializedName("itemName")
    val itemName: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("reviewNum")
    val reviewNum: Int?,
    @SerializedName("saleRate")
    val saleRate: String?,
    @SerializedName("score")
    val score: Double?,
    @SerializedName("subCategory")
    val subCategory: String?,
    @SerializedName("subCategoryId")
    val subCategoryId: Int?
): Parcelable