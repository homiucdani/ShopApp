package com.example.shopapp.presentation.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.shopapp.R

@Composable
fun CartSummary(
    totalPrice: Double,
    subtotal: Double,
    deliveryTax: Int,
    tva: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Order Summary",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Surface(
            tonalElevation = 5.dp,
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                OrderSummaryItem(
                    title = stringResource(R.string.subtotal),
                    price = subtotal,
                    textStyle = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Light
                    )
                )

                OrderSummaryItem(
                    title = stringResource(R.string.delivery),
                    price = deliveryTax.toDouble(),
                    textStyle = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Light
                    )
                )

                OrderSummaryItem(
                    title = stringResource(R.string.total_tax),
                    price = tva.toDouble(),
                    textStyle = TextStyle(
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Light
                    )
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                )

                OrderSummaryItem(
                    title = stringResource(R.string.total),
                    price = totalPrice,
                    textStyle = TextStyle(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Composable
fun OrderSummaryItem(
    title: String,
    price: Double,
    textStyle: TextStyle
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = textStyle
        )

        Text(
            text = "$%.2f".format(price),
            style = textStyle
        )
    }
}