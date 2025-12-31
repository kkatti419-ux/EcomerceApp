package com.example.apiintegration.data.mapper

import com.example.apiintegration.data.local.entity.UserDetailsEntity
import com.example.apiintegration.domain.model.UserData.UserDetails
import com.example.apiintegration.data.local.entity.UserEntity

fun UserDetails.toEntity(): UserDetailsEntity {
    return UserDetailsEntity(
        id = id,
        username = username,
        password = password,
        token = token
    )
}

fun UserDetailsEntity.toDomain(): UserDetails {
    return UserDetails(
        id = id,
        username = username,
        password = password,
        token = token
    )
}
