package com.example.shopapp.presentation.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun BottomHomeBar(
    onHomeClick: () -> Unit,
    onCartClick: () -> Unit
) {
    BottomAppBar(
        actions = {
            HomeButton(
                onClick = onHomeClick
            )
            CartButton(
                onClick = onCartClick
            )
        }
    )
}

@Composable
private fun HomeButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(imageVector = Icons.Default.Home, contentDescription = null)
    }
}

@Composable
private fun CartButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)
    }
}