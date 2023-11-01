package com.example.shopapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class MainActivityViewModel @Inject constructor(
    private val localDataSource: LocalDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(MainActivityState())
    val state: StateFlow<MainActivityState> = _state

    init {
        observeCartItemsSize()
    }

    fun onEvent(event: MainActivityEvent) {
        when (event) {
            is MainActivityEvent.SelectedBottomTab -> {
                _state.update {
                    it.copy(
                        currentSelectedTab = event.index
                    )
                }
            }
        }
    }

    private fun observeCartItemsSize() {
        viewModelScope.launch(Dispatchers.IO) {
            localDataSource.getAllCartItems().collect { cartItems ->
                withContext(Dispatchers.Main) {
                    _state.update {
                        it.copy(
                            cartItemSize = cartItems.size
                        )
                    }
                }
            }
        }
    }
}