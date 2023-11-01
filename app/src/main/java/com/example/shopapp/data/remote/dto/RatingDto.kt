package com.example.shopapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RatingDto(
    val rate: Double,
    val count: Int
)
