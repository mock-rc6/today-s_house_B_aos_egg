package com.example.todayhome.config.store


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventImg(
    @SerializedName("description")
    val description: String?,
    @SerializedName("storeEventId")
    val storeEventId: Int?,
    @SerializedName("storeEventImgUrl")
    val storeEventImgUrl: String?
): Parcelable