package com.example.shopapp.data.repository

import com.example.shopapp.data.local.dao.CartDao
import com.example.shopapp.data.mapper.toCart
import com.example.shopapp.data.mapper.toCartEntity
import com.example.shopapp.domain.model.Cart
import com.example.shopapp.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val cartDao: CartDao
) : LocalDataSource {

    override suspend fun upsertToCart(cart: Cart) {
        cartDao.upsertToCart(cart.toCartEntity())
    }

    override suspend fun deleteCartItem(id: Int) {
        cartDao.deleteCartItem(id)
    }

    override fun getAllCartItems(): Flow<List<Cart>> {
        return cartDao.getAllCartItems().map { items ->
            items.map {
                it.toCart()
            }
        }
    }
}