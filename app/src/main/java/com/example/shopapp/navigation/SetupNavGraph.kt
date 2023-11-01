package com.example.shopapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.shopapp.core.presentation.util.UiEvent
import com.example.shopapp.presentation.cart.CartEvent
import com.example.shopapp.presentation.cart.CartScreen
import com.example.shopapp.presentation.cart.CartViewModel
import com.example.shopapp.presentation.home.HomeEvent
import com.example.shopapp.presentation.home.HomeScreen
import com.example.shopapp.presentation.home.HomeViewModel
import com.example.shopapp.presentation.product_details.ProductDetailsScreen
import com.example.shopapp.presentation.product_details.ProductDetailsViewModel

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        home(
            navigateToDetailsProduct = { productId ->
                navHostController.navigate(Screen.ProductDetails.passProductId(productId))
            }
        )

        productDetails(
            onBackClick = {
                navHostController.navigate(Screen.Home.route) {
                    popUpTo(route = Screen.Home.route) {
                        inclusive = true
                    }
                }
            }
        )

        cart(
            onBackClick = {
                navHostController.popBackStack()
            }
        )

    }
}

fun NavGraphBuilder.home(
    navigateToDetailsProduct: (Int) -> Unit
) {
    composable(
        route = Screen.Home.route
    ) {

        val homeViewModel: HomeViewModel = hiltViewModel()
        val state = homeViewModel.state.collectAsState().value

        HomeScreen(
            state = state,
            onEvent = { event ->
                when (event) {
                    is HomeEvent.OnProductClick -> {
                        navigateToDetailsProduct(event.productId)
                    }

                    else -> homeViewModel.onEvent(event)
                }
            }
        )
    }
}

fun NavGraphBuilder.productDetails(
    onBackClick: () -> Unit
) {
    composable(
        route = Screen.ProductDetails.route,
        arguments = listOf(
            navArgument(
                name = "productId"
            ) {
                type = NavType.IntType
            }
        )
    ) {
        val productDetailsViewModel: ProductDetailsViewModel = hiltViewModel()
        val state = productDetailsViewModel.state.collectAsState().value

        LaunchedEffect(key1 = true) {
            productDetailsViewModel.uiState.collect { uiEvent ->
                when (uiEvent) {
                    UiEvent.OnBackClick -> {
                        onBackClick()
                    }
                }
            }
        }

        ProductDetailsScreen(
            state = state,
            onEvent = { event ->
                productDetailsViewModel.onEvent(event)
            }
        )
    }
}

fun NavGraphBuilder.cart(
    onBackClick: () -> Unit
) {
    composable(
        route = Screen.Cart.route
    ) {
        val cartViewModel: CartViewModel = hiltViewModel()
        val state = cartViewModel.state.collectAsState().value
        CartScreen(
            state = state,
            onEvent = { cartEvent ->
                when (cartEvent) {
                    CartEvent.OnBackClick -> {
                        onBackClick()
                    }

                    else -> cartViewModel.onEvent(cartEvent)
                }
            }
        )
    }
}
