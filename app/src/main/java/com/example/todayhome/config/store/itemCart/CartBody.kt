package com.example.todayhome.config.store.itemCart

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "CartTable")
data class CartBody(
    @SerializedName(value = "optionId") val optionId: Long,
    @SerializedName(value = "number") val number: Int

    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
