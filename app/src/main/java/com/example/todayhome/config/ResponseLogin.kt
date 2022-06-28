package com.example.todayhome.config

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Result?

)

data class Result(
    @SerializedName("userId") val userIdx: Long,
    @SerializedName("jwt") val jwt: String
)
