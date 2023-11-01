package com.example.shopapp.presentation.home

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.shopapp.presentation.home.components.HomeContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Shop App")
                }
            )
        }
    ) { paddingValues ->
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            HomeContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                allProducts = state.allProducts,
                selectedCategoryItems = state.singleCategory,
                onCategorySelected = { category ->
                    onEvent(HomeEvent.OnCategoryClick(category))
                },
                onProductClick = { product ->
                    onEvent(HomeEvent.OnProductClick(product.id))
                },
                selectedTab = state.selectedTabIndex,
                onTabSelected = { index ->
                    onEvent(HomeEvent.OnTabSelected(index))
                }
            )
        }
    }
}