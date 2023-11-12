package com.example.shopapp.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.domain.model.Cart
import com.example.shopapp.domain.repository.LocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val localDataSource: LocalDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(CartState())
    val state: StateFlow<CartState> = _state

    init {
        observeCartItems()
    }

    fun onEvent(cartEvent: CartEvent) {
        when (cartEvent) {
            is CartEvent.OnAddQuantity -> {

                state.value.cartItems.filter { currentCart ->
                    currentCart.id == cartEvent.cart.id
                }.map { cart ->

                    val quantity = cart.quantity.plus(1)
                    val totalPrice = cart.normalPrice * quantity

                    viewModelScope.launch(Dispatchers.IO) {
                        localDataSource.upsertToCart(
                            Cart(
                                id = cart.id,
                                title = cart.title,
                                image = cart.image,
                                quantity = quantity,
                                totalPrice = totalPrice,
                                normalPrice = cart.normalPrice
                            )
                        )
                    }
                }
            }

            is CartEvent.OnRemoveQuantity -> {
                state.value.cartItems.filter { currentCart ->
                    currentCart.id == cartEvent.cart.id
                }.map { cart ->

                    val quantity = cart.quantity.minus(1)
                    val totalPrice = cart.normalPrice * quantity

                    viewModelScope.launch(Dispatchers.IO) {
                        localDataSource.upsertToCart(
                            Cart(
                                id = cart.id,
                                title = cart.title,
                                image = cart.image,
                                quantity = quantity,
                                totalPrice = totalPrice,
                                normalPrice = cart.normalPrice
                            )
                        )
                    }
                }
            }

            else -> Unit
        }
    }

    private fun observeCartItems() {
        viewModelScope.launch(Dispatchers.IO) {
            localDataSource.getAllCartItems().collect { items ->
                withContext(Dispatchers.Main) {
                    _state.update {
                        it.copy(
                            cartItems = items
                        )
                    }
                }
                calculateOrderSummary(items)
            }
        }
    }

    private fun calculateOrderSummary(cartItems: List<Cart>) {
        val subtotal = cartItems.sumOf { it.totalPrice }
        val total = subtotal + state.value.deliveryTax + state.value.tva
        _state.update {
            it.copy(
                subtotal = subtotal,
                total = total
            )
        }
    }


}