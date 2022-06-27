package com.example.todayhome.src.main.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todayhome.src.main.store.data.StoreHomeBannerItem
import com.example.todayhome.src.main.store.data.menuGridItem
import com.example.todayhome.src.main.store.data.menuGriditem2

class storeHomeViewModel : ViewModel() {
    private val _bannerItemList: MutableLiveData<List<StoreHomeBannerItem>> = MutableLiveData()
    private val _gridItemList: MutableLiveData<List<menuGridItem>> = MutableLiveData()
    private val _gridItemList2: MutableLiveData<List<menuGriditem2>> = MutableLiveData()

    val bannerItemList: LiveData<List<StoreHomeBannerItem>>
        get() = _bannerItemList


    fun setBannerItems(list: List<StoreHomeBannerItem>){
        _bannerItemList.value = list
    }

    val gridItemList: LiveData<List<menuGridItem>>
        get() = _gridItemList


    fun setGridItems(list: List<menuGridItem>) {
        _gridItemList.value = list
    }

    val gridItemList2: LiveData<List<menuGriditem2>>
        get() = _gridItemList2


    fun setGridItems2(list: List<menuGriditem2>) {
        _gridItemList2.value = list
    }

}