package com.example.shopapp.presentation.product_details.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shopapp.R

@Composable
fun BottomAddToCart(
    onAddToCartClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        onClick = onAddToCartClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red.copy(alpha = 0.9f)
        )
    ) {
        Text(text = stringResource(R.string.add_to_cart))
    }
}