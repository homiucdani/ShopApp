package com.example.shopapp.domain.model

data class Cart(
    val id: Int,
    val title: String,
    val image: String,
    val totalPrice: Double,
    val normalPrice: Double,
    val quantity: Int
)
