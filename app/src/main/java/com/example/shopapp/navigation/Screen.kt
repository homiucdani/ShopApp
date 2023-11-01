package com.example.shopapp.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home_screen")

    object ProductDetails : Screen("product_details_screen/{productId}") {
        fun passProductId(productId: Int): String {
            return "product_details_screen/$productId"
        }
    }

    object Cart : Screen("cart_screen")

}

