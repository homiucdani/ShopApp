package com.example.shopapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shopapp.data.local.dao.CartDao
import com.example.shopapp.data.local.entity.CartEntity

@Database(
    entities = [
        CartEntity::class
    ],
    version = 1
)
abstract class ShopDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao
}
