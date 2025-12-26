package com.example.apiintegration.di

import android.app.Application
import androidx.room.Room
import com.example.apiintegration.data.local.AppDatabase
import com.example.apiintegration.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "api_integration.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao
    }

    @Provides
    @Singleton
    fun providePostDao(db: AppDatabase): com.example.apiintegration.data.local.dao.PostDao {
        return db.postDao
    }
}
