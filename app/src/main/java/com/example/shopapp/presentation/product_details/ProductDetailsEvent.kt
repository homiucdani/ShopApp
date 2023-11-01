package com.example.shopapp.presentation.product_details

import com.example.shopapp.domain.model.Product

sealed class ProductDetailsEvent {
    object OnBackClick : ProductDetailsEvent()
    data class AddProductToCart(val product: Product) : ProductDetailsEvent()
    data class OnQuantityAdd(val product: Product) : ProductDetailsEvent()
    data class OnQuantityRemove(val product: Product) : ProductDetailsEvent()
}
