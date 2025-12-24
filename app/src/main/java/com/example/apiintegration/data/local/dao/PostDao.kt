package com.example.apiintegration.data.local.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.example.apiintegration.data.local.entity.PostEntity
import com.example.apiintegration.data.local.entity.UserEntity

@Dao
interface PostDao {

    @Upsert
    suspend fun upsertPost(posts: PostEntity)
}