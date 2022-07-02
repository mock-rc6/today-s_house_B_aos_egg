package com.example.todayhome.config.home.api


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    @SerializedName("bannerPic")
    val bannerPic: String?,
    @SerializedName("due")
    val due: String?,
    @SerializedName("eventId")
    val eventId: Int?
): Parcelable