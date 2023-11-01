package com.example.shopapp.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.shopapp.R
import com.example.shopapp.domain.model.Product

private val reusableModifier: Modifier = Modifier
    .padding(20.dp)
    .fillMaxWidth()

@Composable
fun CategoryContent(
    product: Product,
    onProductClick: (Product) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = reusableModifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(30))
                .size(200.dp),
            model = ImageRequest.Builder(context)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .crossfade(true)
                .data(product.image)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = product.title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier.alpha(0.7f),
            text = product.description,
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            ),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) {
                    onProductClick(product)
                }
                .align(Alignment.End),
            text = "See more",
            textDecoration = TextDecoration.Underline
        )
    }
}