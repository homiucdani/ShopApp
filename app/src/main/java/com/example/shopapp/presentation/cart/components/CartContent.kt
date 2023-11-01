package com.example.shopapp.presentation.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shopapp.domain.model.Cart

@Composable
fun CartContent(
    modifier: Modifier = Modifier,
    cartItems: List<Cart>,
    totalPrice: Double,
    subtotal: Double,
    deliveryTax: Int,
    tva: Int,
    onAddQuantity: (Cart) -> Unit,
    onRemoveQuantity: (Cart) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f / 0.8f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = cartItems,
                key = {
                    it.id
                }
            ) { cartItem ->
                CartItem(
                    cart = cartItem,
                    quantity = cartItem.quantity,
                    onAddQuantity = onAddQuantity,
                    onRemoveQuantity = onRemoveQuantity
                )
            }
        }

        CartSummary(
            totalPrice = totalPrice,
            subtotal = subtotal,
            deliveryTax = deliveryTax,
            tva = tva
        )
    }
}