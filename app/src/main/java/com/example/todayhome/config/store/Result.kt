package com.example.todayhome.config.store


import com.google.gson.annotations.SerializedName


data class Result(
    @SerializedName("categoryIdList")
    val categoryIdList: List<Int>?,
    @SerializedName("eventImgs")
    val eventImgs: List<EventImg>?,
    @SerializedName("todaysDealList")
    val todaysDealList: List<TodaysDeal>?
)