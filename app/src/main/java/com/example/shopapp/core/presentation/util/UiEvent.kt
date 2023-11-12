package com.example.shopapp.core.presentation.util

sealed class UiEvent {
    object OnBackClick : UiEvent()
    data class NavigateToRoute(val route: String) : UiEvent()
}
