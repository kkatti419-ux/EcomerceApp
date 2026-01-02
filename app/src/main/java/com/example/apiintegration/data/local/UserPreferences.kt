package com.example.apiintegration.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.apiintegration.common.constants.PreferenceKeys
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun saveCredentials(username: String, password: String,phone:String) {
        sharedPreferences.edit {
            putString(PreferenceKeys.KEY_USERNAME, username)
            putString(PreferenceKeys.KEY_PASSWORD, password)
            putString(PreferenceKeys.PHONE_NUMBER,phone)
        }
    }

    fun saveToken(accessToken: String, refreshToken: String) {
        sharedPreferences.edit {
            putString(PreferenceKeys.KEY_ACCESS_TOKEN, accessToken)
            putString(PreferenceKeys.KEY_REFRESH_TOKEN, refreshToken)
            // Default 30 mins expiry to match typical session length, enabling SessionManager.isLoggedIn()
            putLong(PreferenceKeys.EXPIRY_TIME, System.currentTimeMillis() + 30 * 60 * 1000)
        }
    }

    fun getUsername(): String? {
        return sharedPreferences.getString(PreferenceKeys.KEY_USERNAME, null)
    }

    fun getPassword(): String? {
        return sharedPreferences.getString(PreferenceKeys.KEY_PASSWORD, null)
    }



    fun getAccessToken(): String? {
        return sharedPreferences.getString(PreferenceKeys.KEY_ACCESS_TOKEN, null)
    }

    fun getRefreshToken(): String? {
        return sharedPreferences.getString(PreferenceKeys.KEY_REFRESH_TOKEN, null)
    }

    fun getPhoneNumber(): String? {
        return sharedPreferences.getString(PreferenceKeys.PHONE_NUMBER, null)
    }
}
