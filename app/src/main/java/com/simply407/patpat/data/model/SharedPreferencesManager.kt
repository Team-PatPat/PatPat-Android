package com.simply407.patpat.data.model

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {

    private const val FILE_NAME = "user_info"
    private const val KEY_ONBOARDING_SHOWN = "onboarding_shown"
    private const val KEY_USER_IS_LOGGED_IN = "user_is_logged_in"
    private const val KEY_FIRST_JOIN_COMPLETE = "first_join_complete"

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

    fun setUserIsLoggedIn(shown: Boolean) {
        sharedPref.edit().putBoolean(KEY_USER_IS_LOGGED_IN, shown).apply()
    }

    fun isUserLoggedIn(): Boolean {
        return sharedPref.getBoolean(KEY_USER_IS_LOGGED_IN, false)
    }

    fun setFirstJoinComplete(shown: Boolean) {
        sharedPref.edit().putBoolean(KEY_FIRST_JOIN_COMPLETE, shown).apply()
    }

    fun isFirstJoinComplete(): Boolean {
        return sharedPref.getBoolean(KEY_FIRST_JOIN_COMPLETE, false)
    }

}