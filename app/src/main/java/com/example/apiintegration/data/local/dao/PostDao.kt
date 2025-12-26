package com.example.apiintegration.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.apiintegration.data.local.entity.PostEntity
import com.example.apiintegration.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Upsert
    suspend fun upsertPost(posts: PostEntity)

//
//    @Query("SELECT * FROM users")
//    fun getAllPosts(): Flow<List<PostEntity>>
}