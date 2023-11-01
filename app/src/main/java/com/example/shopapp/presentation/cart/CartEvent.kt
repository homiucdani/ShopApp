package com.example.shopapp.presentation.cart

import com.example.shopapp.domain.model.Cart

sealed class CartEvent {

    object OnBackClick : CartEvent()
    data class OnAddQuantity(val cart: Cart) : CartEvent()
    data class OnRemoveQuantity(val cart: Cart) : CartEvent()
}
