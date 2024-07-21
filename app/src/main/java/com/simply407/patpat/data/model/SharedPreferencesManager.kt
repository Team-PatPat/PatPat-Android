package com.simply407.patpat.data.model

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {

    private const val FILE_NAME = "user_info"
    private const val KEY_ONBOARDING_SHOWN = "onboarding_shown"

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

}