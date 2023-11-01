package com.example.shopapp.di

import android.content.Context
import androidx.room.Room
import com.example.shopapp.core.util.Constants
import com.example.shopapp.data.local.dao.CartDao
import com.example.shopapp.data.local.database.ShopDatabase
import com.example.shopapp.data.repository.LocalDataSourceImpl
import com.example.shopapp.domain.repository.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesShopDatabase(@ApplicationContext context: Context): ShopDatabase {
        return Room.databaseBuilder(
            context,
            ShopDatabase::class.java,
            Constants.SHOP_DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesCartDao(shopDatabase: ShopDatabase): CartDao {
        return shopDatabase.cartDao()
    }

    @Provides
    @Singleton
    fun providesLocalDataSource(cartDao: CartDao): LocalDataSource {
        return LocalDataSourceImpl(cartDao)
    }
}