package com.example.shopapp.di

import com.example.shopapp.data.remote.FakeStoreApi
import com.example.shopapp.data.repository.RemoteDataSourceImpl
import com.example.shopapp.domain.repository.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(FakeStoreApi.BASE_URl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesFakeStoreApi(retrofit: Retrofit): FakeStoreApi {
        return retrofit.create(FakeStoreApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(fakeStoreApi: FakeStoreApi): RemoteDataSource {
        return RemoteDataSourceImpl(fakeStoreApi)
    }
}