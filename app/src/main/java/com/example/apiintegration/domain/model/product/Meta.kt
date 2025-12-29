package com.example.apiintegration.domain.model.product

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meta(
    val createdAt: String,
    val updatedAt: String,
    val barcode: String,
    val qrCode: String
)
