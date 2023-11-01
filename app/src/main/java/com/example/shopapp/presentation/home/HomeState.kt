package com.example.shopapp.presentation.home

import com.example.shopapp.domain.model.Product

data class HomeState(
    val allProducts: List<Product> = emptyList(),
    val singleCategory: List<Product> = emptyList(),
    val selectedTabIndex: Int = 0,
    val isLoading:Boolean = false
)
