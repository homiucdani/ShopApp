package com.example.shopapp.domain.repository

import com.example.shopapp.domain.model.Cart
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun upsertToCart(cart: Cart)

    suspend fun deleteCartItem(id: Int)

    fun getAllCartItems(): Flow<List<Cart>>
}