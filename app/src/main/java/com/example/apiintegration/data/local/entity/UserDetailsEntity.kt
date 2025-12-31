package com.example.apiintegration.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "userdata")
data class UserDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String,
    val token: String
)
