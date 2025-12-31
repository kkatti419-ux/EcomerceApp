package com.example.apiintegration.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.apiintegration.data.local.entity.UserDetailsEntity
import com.example.apiintegration.data.local.entity.UserEntity
import com.example.apiintegration.domain.model.UserData.UserDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDetailsDao {

    @Upsert
    suspend fun insertData(userDetails: UserDetailsEntity)


    @Query("SELECT * FROM userdata")
    fun getAllUsers(): Flow<List<UserDetailsEntity>>
}