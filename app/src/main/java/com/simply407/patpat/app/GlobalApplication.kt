package com.simply407.patpat.app

import android.app.Application
import android.content.SharedPreferences
import com.kakao.sdk.common.KakaoSdk
import com.simply407.patpat.BuildConfig
import com.simply407.patpat.data.model.ChatLocalDB
import com.simply407.patpat.data.model.SharedPreferencesManager

class GlobalApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        SharedPreferencesManager.init(this)
        ChatLocalDB.init(this)

        // Kakao Sdk 초기화
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)


    }
}