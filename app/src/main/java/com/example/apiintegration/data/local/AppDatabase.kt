package com.example.apiintegration.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apiintegration.data.local.dao.PostDao
import com.example.apiintegration.data.local.dao.UserDao
import com.example.apiintegration.data.local.dao.UserDetailsDao
import com.example.apiintegration.data.local.entity.PostEntity
import com.example.apiintegration.data.local.entity.UserEntity
import com.example.apiintegration.data.local.entity.UserDetailsEntity


@Database(
    entities = [
        UserEntity::class,
        PostEntity::class , // ✅ REQUIRED
        UserDetailsEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val postDao: PostDao

    abstract val userDetailsDao: UserDetailsDao
}
