package com.example.shopapp.presentation.main

sealed class MainActivityEvent {
    data class SelectedBottomTab(val index: Int) : MainActivityEvent()
}
