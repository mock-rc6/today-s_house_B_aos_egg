package com.example.todayhome.src.main.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class storeHomeViewModel : ViewModel() {
    private val _bannerItemList: MutableLiveData<List<StoreHomeBannerItem>> = MutableLiveData()

    val bannerItemList: LiveData<List<StoreHomeBannerItem>>
        get() = _bannerItemList


    fun setBannerItems(list: List<StoreHomeBannerItem>){
        _bannerItemList.value = list
    }

}