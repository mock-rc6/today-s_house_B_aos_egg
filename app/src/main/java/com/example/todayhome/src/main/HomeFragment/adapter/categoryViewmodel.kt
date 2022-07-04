package com.example.todayhome.src.main.HomeFragment.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class categoryViewmodel : ViewModel() {
    private val _bannerItemList: MutableLiveData<List<categoryItem>> = MutableLiveData()


    val bannerItemList: LiveData<List<categoryItem>>
        get() = _bannerItemList


    fun setBannerItems(list: List<categoryItem>) {
        _bannerItemList.value = list
    }
}