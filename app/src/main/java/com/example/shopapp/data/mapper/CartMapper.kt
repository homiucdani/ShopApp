package com.example.shopapp.data.mapper

import com.example.shopapp.data.local.entity.CartEntity
import com.example.shopapp.domain.model.Cart

fun CartEntity.toCart(): Cart {
    return Cart(id, title, image, price, normalPrice, quantity)
}

fun Cart.toCartEntity(): CartEntity {
    return CartEntity(
        id, title, image, totalPrice, quantity, normalPrice
    )
}