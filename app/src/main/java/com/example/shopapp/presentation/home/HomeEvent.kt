package com.example.shopapp.presentation.home

sealed class HomeEvent {

    data class OnCategoryClick(val categoryName: String) : HomeEvent()
    data class OnProductClick(val productId: Int) : HomeEvent()
    data class OnTabSelected(val index: Int) : HomeEvent()
}
