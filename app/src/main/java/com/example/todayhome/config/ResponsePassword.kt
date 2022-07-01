package com.example.todayhome.config

import com.google.gson.annotations.SerializedName

data class ResponsePassword(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: String

)


