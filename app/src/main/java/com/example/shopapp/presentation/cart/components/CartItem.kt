package com.example.shopapp.presentation.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.shopapp.R
import com.example.shopapp.domain.model.Cart

@Composable
fun CartItem(
    cart: Cart,
    quantity: Int,
    onAddQuantity: (Cart) -> Unit,
    onRemoveQuantity: (Cart) -> Unit,
) {

    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f / 0.2f),
        tonalElevation = 5.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(5.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .size(80.dp),
                model = ImageRequest.Builder(context)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .crossfade(true)
                    .data(cart.image)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.weight(0.7f),
                        text = cart.title,
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = MaterialTheme.typography.titleSmall.fontSize
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Text(
                        modifier = Modifier.weight(0.3f),
                        text = "$${cart.normalPrice}",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize
                        ),
                        color = Color.Red,
                        textAlign = TextAlign.Center
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$%.2f".format(cart.totalPrice),
                        style = TextStyle(
                            fontWeight = FontWeight.Light,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize
                        ),
                        color = Color.Red
                    )

                    QuantityCartItem(
                        quantity = quantity,
                        onAddQuantity = {
                            onAddQuantity(cart)
                        },
                        onRemoveQuantity = {
                            onRemoveQuantity(cart)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun QuantityCartItem(
    quantity: Int,
    onAddQuantity: () -> Unit,
    onRemoveQuantity: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onRemoveQuantity,
            enabled = quantity > 1
        ) {
            Icon(
                modifier = Modifier.size(15.dp),
                painter = painterResource(id = R.drawable.remove),
                contentDescription = null
            )
        }

        Surface(
            shape = CircleShape,
            tonalElevation = 5.dp
        ) {
            Text(
                text = "$quantity",
                style = TextStyle(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
        }

        IconButton(
            onClick = onAddQuantity,
            enabled = quantity < 99
        ) {
            Icon(
                modifier = Modifier.size(15.dp),
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
    }
}