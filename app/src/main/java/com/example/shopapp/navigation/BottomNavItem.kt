package com.example.shopapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
)

val listOfNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route = Screen.Home.route,
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Outlined.Home,
        badgeCount = null
    ),
    BottomNavItem(
        title = "Cart",
        route = Screen.Cart.route,
        selectedIcon = Icons.Default.ShoppingCart,
        unselectedIcon = Icons.Outlined.ShoppingCart,
        badgeCount = null
    )
)
