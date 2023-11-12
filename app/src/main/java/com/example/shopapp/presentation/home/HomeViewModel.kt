package com.example.shopapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.core.presentation.util.UiEvent
import com.example.shopapp.domain.repository.LocalDataSource
import com.example.shopapp.domain.repository.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent

    init {
        loadData()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnCategoryClick -> {
                getSelectedCategoryProducts(event.categoryName)
            }

            is HomeEvent.OnTabSelected -> {
                _state.update {
                    it.copy(
                        selectedTopTabIndex = event.index
                    )
                }
            }

            is HomeEvent.SelectedBottomTab -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            selectedBottomTopIndex = event.index
                        )
                    }
                    _uiEvent.emit(UiEvent.NavigateToRoute(route = event.route))
                }
            }

            else -> Unit
        }
    }

    private fun loadData() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            val deferredAllProducts = async { remoteDataSource.getAllProducts() }
            val deferredCartItems = async { localDataSource.getAllCartItems() }

            deferredAllProducts.await().run {
                withContext(Dispatchers.Main) {
                    _state.update {
                        it.copy(
                            allProducts = this@run,
                            isLoading = false
                        )
                    }
                }
            }
            deferredCartItems.await().collect { items ->
                withContext(Dispatchers.Main) {
                    _state.update {
                        it.copy(
                            cartItemSize = items.size
                        )
                    }
                }
            }

        }
    }

    private fun getSelectedCategoryProducts(category: String) {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            val data = remoteDataSource.getCategoryByName(category)

            withContext(Dispatchers.Main) {
                _state.update {
                    it.copy(
                        singleCategory = data,
                        isLoading = false
                    )
                }
            }
        }
    }
}