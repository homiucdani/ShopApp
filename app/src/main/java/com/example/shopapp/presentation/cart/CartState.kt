package com.example.shopapp.presentation.cart

import com.example.shopapp.domain.model.Cart

data class CartState(
    val cartItems: List<Cart> = emptyList(),
    val quantity: Int = 1,
    val deliveryTax: Int = 10,
    val tva: Int = 5,
    val subtotal: Double = 0.0,
    val total: Double = 0.0
)
