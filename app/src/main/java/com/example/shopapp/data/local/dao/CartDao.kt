package com.example.shopapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.shopapp.data.local.entity.CartEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Upsert
    suspend fun upsertToCart(cartEntity: CartEntity)

    @Query("DELETE FROM cart_item_table WHERE id = :id")
    suspend fun deleteCartItem(id: Int)

    @Query("SELECT * FROM cart_item_table ORDER BY id ASC")
    fun getAllCartItems(): Flow<List<CartEntity>>
}