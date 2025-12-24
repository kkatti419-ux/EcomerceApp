package com.example.apiintegration.data.repository

import com.example.apiintegration.common.utils.AppLogger
import com.example.apiintegration.data.local.dao.PostDao
import com.example.apiintegration.data.local.entity.PostEntity
import com.example.apiintegration.domain.model.PostList
import com.example.apiintegration.domain.model.UserProfile
import com.example.apiintegration.domain.repository.LocalPostRepository
import javax.inject.Inject

class LocalPostRepositoryImpl @Inject constructor(
    private val dao: PostDao
): LocalPostRepository {
    override suspend fun upsertPost(user: PostList) {
       val result= dao.upsertPost(user.toEntity())
        AppLogger.d("DB_CHECKER","Insert result: $result")
    }

    private fun PostList.toEntity(): PostEntity {
        return PostEntity(
            id = id ?: 0,
            title = title,
            body = body
        )
    }

    fun PostEntity.toDomain(): PostList {
        return PostList(
            id = id,
            title = title,
            body = body
        )
    }



}