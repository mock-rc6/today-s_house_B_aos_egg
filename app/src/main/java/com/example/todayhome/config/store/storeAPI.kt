package com.example.todayhome.config.store



import com.google.gson.annotations.SerializedName




data class storeAPI(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("isSuccess")
    val isSuccess: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("result")
    val result:  Result?
)