package com.example.apiintegration.data.local.session

import android.content.SharedPreferences
import com.example.apiintegration.common.constants.PreferenceKeys
import javax.inject.Inject

class SessionManager @Inject constructor(
    private val prefs: SharedPreferences
) {

    fun isLoggedIn(): Boolean {
        val token = prefs.getString(PreferenceKeys.KEY_ACCESS_TOKEN, null)
        val expiry = prefs.getLong(PreferenceKeys.EXPIRY_TIME, 0L)

        return token != null && System.currentTimeMillis() < expiry
    }
}
