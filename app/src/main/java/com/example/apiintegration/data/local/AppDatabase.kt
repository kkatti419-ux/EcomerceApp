package com.example.apiintegration.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apiintegration.data.local.dao.PostDao
import com.example.apiintegration.data.local.dao.UserDao
import com.example.apiintegration.data.local.entity.PostEntity
import com.example.apiintegration.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        PostEntity::class   // ✅ REQUIRED
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val postDao: PostDao
}
