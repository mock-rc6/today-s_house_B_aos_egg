package com.example.todayhome.config.kakao_api

import android.app.Application
import com.example.todayhome.BuildConfig
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, BuildConfig.kakaoApi)
    }
}