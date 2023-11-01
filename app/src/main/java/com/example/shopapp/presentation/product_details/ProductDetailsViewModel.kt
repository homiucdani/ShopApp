package com.example.shopapp.presentation.product_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.core.presentation.util.UiEvent
import com.example.shopapp.domain.model.Cart
import com.example.shopapp.domain.repository.LocalDataSource
import com.example.shopapp.domain.repository.RemoteDataSource
import com.example.shopapp.domain.services.ShopNotification
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val notification: ShopNotification
) : ViewModel() {

    private val _state = MutableStateFlow(ProductDetailsState())
    val state: StateFlow<ProductDetailsState> = _state

    private val _uiState = MutableSharedFlow<UiEvent>()
    val uiState: SharedFlow<UiEvent> = _uiState

    init {
        observeProductClicked()
    }

    fun onEvent(event: ProductDetailsEvent) {
        when (event) {
            ProductDetailsEvent.OnBackClick -> {
                viewModelScope.launch {
                    _uiState.emit(UiEvent.OnBackClick)
                }
            }

            is ProductDetailsEvent.AddProductToCart -> {
                viewModelScope.launch {
                    val product = event.product
                    upsertItemToCart(
                        Cart(
                            id = product.id,
                            title = product.title,
                            image = product.image,
                            totalPrice = (product.price * state.value.productQuantity),
                            quantity = state.value.productQuantity,
                            normalPrice = product.price
                        )
                    )
                }
            }

            is ProductDetailsEvent.OnQuantityAdd -> {
                val quantity = state.value.productQuantity.plus(1)
                val totalPrice = event.product.price * quantity
                _state.update {
                    it.copy(
                        productQuantity = quantity,
                        totalPriceOnQuantity = totalPrice
                    )
                }
            }

            is ProductDetailsEvent.OnQuantityRemove -> {
                val quantity = state.value.productQuantity.minus(1)
                val totalPrice = event.product.price * quantity
                _state.update {
                    it.copy(
                        productQuantity = quantity,
                        totalPriceOnQuantity = totalPrice
                    )
                }
            }

            else -> Unit
        }
    }

    private fun upsertItemToCart(cart: Cart) {
        viewModelScope.launch(Dispatchers.IO) {
            localDataSource.upsertToCart(cart)
            notification.addToCartNotification(cart.title, "See more details.")
            _uiState.emit(UiEvent.OnBackClick)
        }
    }

    private fun observeProductClicked() {
        val productId = savedStateHandle.get<Int>("productId")
        if (productId != null) {
            viewModelScope.launch(Dispatchers.IO) {
                val product = remoteDataSource.getProductById(productId)
                if (product != null) {
                    withContext(Dispatchers.Main) {
                        _state.update {
                            it.copy(
                                product = product,
                                totalPriceOnQuantity = product.price
                            )
                        }
                    }
                }
            }
        }
    }
}