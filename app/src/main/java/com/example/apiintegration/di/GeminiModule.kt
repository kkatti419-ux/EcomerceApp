package com.example.apiintegration.di

import com.example.apiintegration.data.remote.GeminiApi

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

// GeminiModule.kt
// This is a Hilt Module. It tells Hilt how to create instances of our classes.
// Specifically, it provides the GeminiApi instance.
// We use a separate module to keep the Gemini-specific configuration isolated.

@Module
@InstallIn(SingletonComponent::class)
object GeminiModule {

    @Provides
    @Singleton
    fun provideGeminiApi(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): GeminiApi {
        // We create a new Retrofit instance here because we might want different configuration
        // (like base URL, though we override it in the interface) or just to be safe.
        // In this case, we reuse the OkHttpClient and Moshi provided by the main NetworkModule.

        return Retrofit.Builder()
            .baseUrl("https://generativelanguage.googleapis.com/") // Base URL is required but overridden in the interface
            .client(okHttpClient).addConverterFactory(MoshiConverterFactory.create(moshi)).build()
            .create(GeminiApi::class.java)
    }
}
