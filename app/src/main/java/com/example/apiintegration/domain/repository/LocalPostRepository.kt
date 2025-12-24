package com.example.apiintegration.domain.repository

import com.example.apiintegration.domain.model.PostList
import com.example.apiintegration.domain.model.UserProfile

interface LocalPostRepository {
    suspend fun upsertPost(user: PostList)
}