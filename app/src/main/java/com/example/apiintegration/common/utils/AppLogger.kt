package com.example.apiintegration.common.utils



import android.util.Log

object AppLogger {

    private const val DEFAULT_TAG = "AppLog"

    fun d(message: String, tag: String = DEFAULT_TAG) {
        Log.d(tag, message)
    }

    fun e(message: String, tag: String = DEFAULT_TAG, throwable: Throwable? = null) {
        Log.e(tag, message, throwable)
    }

    fun i(message: String, tag: String = DEFAULT_TAG) {
        Log.i(tag, message)
    }

    fun w(message: String, tag: String = DEFAULT_TAG) {
        Log.w(tag, message)
    }
}
