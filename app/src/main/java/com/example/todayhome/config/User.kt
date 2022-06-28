package com.example.todayhome.config

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserTable")
data class User(
    @SerializedName(value = "email") val email: String,
    @SerializedName(value = "name") val name: String,
    @SerializedName(value = "password") val password: String,
    @SerializedName(value = "gender") val gender: String,
    @SerializedName(value = "bornYear") val bornYear: Int?,

    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}