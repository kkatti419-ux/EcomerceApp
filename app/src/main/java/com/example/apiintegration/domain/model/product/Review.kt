package com.example.apiintegration.domain.model.product

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Review(
    val rating: Long,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String
)
