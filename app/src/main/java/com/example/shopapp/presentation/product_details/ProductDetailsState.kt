package com.example.shopapp.presentation.product_details

import com.example.shopapp.domain.model.Product

data class ProductDetailsState(
    val product: Product? = null,
    val productQuantity: Int = 1,
    val totalPriceOnQuantity: Double = 0.0
)
