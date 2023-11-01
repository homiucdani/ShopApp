package com.example.shopapp.data.remote

import com.example.shopapp.data.remote.dto.ProductDto
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeStoreApi {

    companion object {
        const val BASE_URl = "https://fakestoreapi.com"
    }

    @GET("/products")
    suspend fun getAllProducts(): List<ProductDto>

    @GET("/products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): ProductDto

    @GET("/products/category/{category}")
    suspend fun getCategoryByName(
        @Path("category") category: String
    ): List<ProductDto>
}