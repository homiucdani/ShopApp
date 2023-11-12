package com.example.shopapp.presentation.home

import com.example.shopapp.domain.model.Product

data class HomeState(
    val allProducts: List<Product> = emptyList(),
    val singleCategory: List<Product> = emptyList(),
    val selectedTopTabIndex: Int = 0,
    val isLoading:Boolean = false,
    val selectedBottomTopIndex: Int = 0,
    val cartItemSize: Int = 0
)
