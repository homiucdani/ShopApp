package com.example.shopapp.di

import android.app.Application
import com.example.shopapp.data.services.ShopNotificationImpl
import com.example.shopapp.domain.services.ShopNotification
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ServiceModule {

    @Provides
    @ViewModelScoped
    fun providesShopNotification(app: Application): ShopNotification {
        return ShopNotificationImpl(app)
    }
}