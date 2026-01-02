package com.example.apiintegration.di

import android.content.Context
import android.content.SharedPreferences
import com.example.apiintegration.data.local.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {




    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(
            com.example.apiintegration.common.constants.PreferenceKeys.PREF_NAME,
            Context.MODE_PRIVATE
        )
    }

}
