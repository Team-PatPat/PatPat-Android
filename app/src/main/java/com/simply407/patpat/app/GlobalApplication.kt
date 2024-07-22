package com.simply407.patpat.app

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.simply407.patpat.BuildConfig

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Kakao Sdk 초기화
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }
}