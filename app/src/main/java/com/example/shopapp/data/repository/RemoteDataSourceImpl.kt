package com.example.shopapp.data.repository

import com.example.shopapp.data.mapper.toProduct
import com.example.shopapp.data.remote.FakeStoreApi
import com.example.shopapp.domain.model.Product
import com.example.shopapp.domain.repository.RemoteDataSource

class RemoteDataSourceImpl(
    private val fakeStoreApi: FakeStoreApi
) : RemoteDataSource {

    override suspend fun getAllProducts(): List<Product> {
        return try {
            fakeStoreApi.getAllProducts().map {
                it.toProduct()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getProductById(id: Int): Product? {
        return try {
            fakeStoreApi.getProductById(id).toProduct()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getCategoryByName(categoryName: String): List<Product> {
        return try {
            fakeStoreApi.getCategoryByName(categoryName).map {
                it.toProduct()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}