package com.example.shopapp.presentation.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.core.util.Constants
import com.example.shopapp.domain.repository.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            val data = remoteDataSource.getAllProducts()
            withContext(Dispatchers.Main) {
                _state.update {
                    it.copy(
                        allProducts = data,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnCategoryClick -> {
                getSelectedCategoryProducts(event.categoryName)
            }

            is HomeEvent.OnTabSelected -> {
                _state.update {
                    it.copy(
                        selectedTabIndex = event.index
                    )
                }
            }

            else -> Unit
        }
    }

    private fun getSelectedCategoryProducts(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = remoteDataSource.getCategoryByName(category)
            Log.d("TEST", "getSelectedCategoryProducts: $data")
            withContext(Dispatchers.Main) {
                _state.update {
                    it.copy(
                        singleCategory = data
                    )
                }
            }
        }
    }
}