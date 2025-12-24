package com.example.apiintegration.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class UserPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveCredentials(username: String, password: String) {
        sharedPreferences.edit {
            putString("username", username)
                .putString("password", password)
        }
    }
}
