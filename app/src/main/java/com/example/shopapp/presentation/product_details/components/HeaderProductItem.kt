package com.example.shopapp.presentation.product_details.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.shopapp.R
import com.example.shopapp.domain.model.Product

@Composable
fun HeaderProductItem(
    product: Product,
    quantity: Int,
    totalPriceOnQuantity: Double,
    addQuantity: (Product) -> Unit,
    removeQuantity: (Product) -> Unit,
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f / 0.7f),
            tonalElevation = 5.dp,
            shape = RoundedCornerShape(20.dp)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(context)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .crossfade(true)
                    .data(product.image)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = product.title,
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold
            ),
            overflow = TextOverflow.Clip
        )


        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                modifier = Modifier.size(15.dp),
                imageVector = Icons.TwoTone.Star,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    ) {
                        append("${product.rating.rate}")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.6f) else Color.Black.copy(
                                alpha = 0.6f
                            ),
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            fontWeight = FontWeight.Light
                        )
                    ) {
                        append(" Rating")
                    }
                },
                style = TextStyle(
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                modifier = Modifier.padding(start = 30.dp),
                text = "$%.2f".format(totalPriceOnQuantity),
                style = TextStyle(
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold
                )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        removeQuantity(product)
                    },
                    enabled = quantity > 1
                ) {
                    Icon(
                        modifier = Modifier.size(15.dp),
                        painter = painterResource(id = R.drawable.remove),
                        contentDescription = null
                    )
                }

                Surface(
                    modifier = Modifier.size(20.dp),
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
                    onClick = {
                        addQuantity(product)
                    },
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

        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = product.description,
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
        )
    }
}
