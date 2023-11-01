package com.example.shopapp.domain.repository

import com.example.shopapp.domain.model.Product

interface RemoteDataSource {
    suspend fun getAllProducts(): List<Product>
    suspend fun getProductById(id: Int): Product?
    suspend fun getCategoryByName(categoryName: String): List<Product>
}