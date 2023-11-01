package com.example.shopapp.presentation.cart

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.shopapp.R
import com.example.shopapp.presentation.cart.components.CartContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    state: CartState,
    onEvent: (CartEvent) -> Unit
) {

    val context = LocalContext.current

    val notificationLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isPermissionAccepted ->
            if (isPermissionAccepted) {
                Toast.makeText(
                    context,
                    "Permission accepted",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    )

    LaunchedEffect(key1 = true) {
        if (context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                notificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.my_cart),
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.headlineSmall.fontSize
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onEvent(CartEvent.OnBackClick)
                        }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        CartContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp),
            cartItems = state.cartItems,
            totalPrice = state.total,
            subtotal = state.subtotal,
            deliveryTax = state.deliveryTax,
            tva = state.tva,
            onAddQuantity = { cart ->
                onEvent(CartEvent.OnAddQuantity(cart))
            },
            onRemoveQuantity = { cart ->
                onEvent(CartEvent.OnRemoveQuantity(cart))
            }
        )
    }
}