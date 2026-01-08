package com.example.apiintegration.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.apiintegration.common.constants.PreferenceKeys
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesStorage @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) {
    fun saveCredentials(
        username: String,
        firstname: String,
        lastname: String,
        phone: String,
        email: String,
        profileImage: String,
        gender: String,
    ) {
        sharedPreferences.edit {
            putString(PreferenceKeys.KEY_USERNAME, username)
            putString(PreferenceKeys.FIRST_NAME, firstname)
            putString(PreferenceKeys.LAST_NAME, firstname)
            putString(PreferenceKeys.PHONE_NUMBER, phone)
            putString(PreferenceKeys.EMAIL, email)
            putString(PreferenceKeys.PROFILE_IMAGE, profileImage)
            putString(PreferenceKeys.GENDER, gender)
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
