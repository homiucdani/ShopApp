package com.example.shopapp.presentation.product_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.shopapp.presentation.product_details.components.ProductDetailsContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    state: ProductDetailsState,
    onEvent: (ProductDetailsEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onEvent(ProductDetailsEvent.OnBackClick)
                        }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        if (state.product != null) {
            ProductDetailsContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                product = state.product,
                onAddToCartClick = { product ->
                    onEvent(ProductDetailsEvent.AddProductToCart(product))
                },
                quantity = state.productQuantity,
                totalPriceOnQuantity = state.totalPriceOnQuantity,
                addQuantity = { product ->
                    onEvent(ProductDetailsEvent.OnQuantityAdd(product))
                },
                removeQuantity = { product ->
                    onEvent(ProductDetailsEvent.OnQuantityRemove(product))
                }
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Something went wrong, try again later.",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize
                    )
                )
            }
        }
    }
}