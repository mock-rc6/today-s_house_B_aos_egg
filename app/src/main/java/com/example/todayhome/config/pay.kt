package com.example.todayhome.config

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "payTable")
data class pay(
    @SerializedName(value = "optionId") val optionId: Long,
    @SerializedName(value = "kartId") val kartId: List<Long>,
    @SerializedName(value = "points") val points: Int,
    @SerializedName(value = "couponId") val couponId: Long,
    @SerializedName(value = "orderName") val orderName: String,
    @SerializedName(value = "phoneNum") val phoneNum: String,
    @SerializedName(value = "email") val email: String,
    @SerializedName(value = "receivedName") val receivedName: String,
    @SerializedName(value = "receivedPhone") val receivedPhone: String,
    @SerializedName(value = "placeName") val placeName: String,
    @SerializedName(value = "addressCode") val addressCode: String,
    @SerializedName(value = "address") val address: String,
    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}