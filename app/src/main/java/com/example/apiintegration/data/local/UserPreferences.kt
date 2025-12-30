package com.example.apiintegration.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.apiintegration.common.constants.PreferenceKeys

class UserPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PreferenceKeys.PREF_NAME, Context.MODE_PRIVATE)

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
