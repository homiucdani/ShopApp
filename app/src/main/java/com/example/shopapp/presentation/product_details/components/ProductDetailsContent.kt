package com.example.shopapp.presentation.product_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shopapp.domain.model.Product

@Composable
fun ProductDetailsContent(
    modifier: Modifier = Modifier,
    product: Product,
    onAddToCartClick: (Product) -> Unit,
    quantity: Int,
    totalPriceOnQuantity: Double,
    addQuantity: (Product) -> Unit,
    removeQuantity: (Product) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(20.dp)
        ) {
            item {
                HeaderProductItem(
                    product = product,
                    quantity = quantity,
                    totalPriceOnQuantity = totalPriceOnQuantity,
                    addQuantity = addQuantity,
                    removeQuantity = removeQuantity
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 85.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BottomAddToCart(
                onAddToCartClick = {
                    onAddToCartClick(product)
                }
            )
        }
    }
}