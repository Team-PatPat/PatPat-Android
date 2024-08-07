package com.simply407.patpat.data.model

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {

    const val FILE_NAME = "user_info"
    private const val KEY_ONBOARDING_SHOWN = "onboarding_shown"
    private const val KEY_USER_ACCESS_TOKEN = "accessToken"
    private const val KEY_USER_NAME = "user_name"
    private const val KEY_KAKAO_ACCESS_TOKEN = "kakao_access_token"

    private lateinit var sharedPref: SharedPreferences

    fun init(context: Context) {
        sharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    fun setOnboardingShown(shown: Boolean) {
        sharedPref.edit().putBoolean(KEY_ONBOARDING_SHOWN, shown).apply()
    }

    fun isOnboardingShown(): Boolean {
        return sharedPref.getBoolean(KEY_ONBOARDING_SHOWN, false)
    }

    fun setUserAccessToken(accessToken: String) {
        sharedPref.edit().putString(KEY_USER_ACCESS_TOKEN, accessToken).apply()
    }

    fun getUserAccessToken(): String? {
        return sharedPref.getString(KEY_USER_ACCESS_TOKEN, null)
    }

    fun setUserName(name: String) {
        sharedPref.edit().putString(KEY_USER_NAME, name).apply()
    }

    fun getUserName(): String? {
        return sharedPref.getString(KEY_USER_NAME, null)
    }

    fun setKakaoAccessToken(accessToken: String) {
        sharedPref.edit().putString(KEY_KAKAO_ACCESS_TOKEN, accessToken).apply()
    }

    fun getKakaoAccessToken(): String? {
        return sharedPref.getString(KEY_KAKAO_ACCESS_TOKEN, null)
    }

    fun clearAllExceptOnboarding() {
        sharedPref.edit().apply {
            remove(KEY_USER_ACCESS_TOKEN)
            remove(KEY_USER_NAME)
            remove(KEY_KAKAO_ACCESS_TOKEN)
            apply()
        }
    }

}