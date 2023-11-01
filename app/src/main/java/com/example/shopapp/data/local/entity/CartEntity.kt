package com.example.shopapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item_table")
data class CartEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val image: String,
    val price: Double,
    val quantity: Int,
    val normalPrice: Double
)
