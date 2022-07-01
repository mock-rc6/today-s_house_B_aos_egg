package com.example.todayhome.config

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "UserTable")
data class UserPassword(

    @SerializedName(value = "password") val password: String,

    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}