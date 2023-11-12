package com.example.shopapp.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.shopapp.navigation.Screen
import com.example.shopapp.navigation.listOfNavItems
import com.example.shopapp.presentation.home.components.HomeContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Shop App")
                }
            )
        },
        bottomBar = {
            NavigationBar {
                listOfNavItems.forEachIndexed { index, bottomNavItem ->
                    NavigationBarItem(
                        selected = state.selectedBottomTopIndex == index,
                        onClick = {
                            onEvent(
                                HomeEvent.SelectedBottomTab(
                                    index = index,
                                    route = bottomNavItem.route
                                )
                            )
                        },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (bottomNavItem.route == Screen.Cart.route) {
                                        if (state.cartItemSize != 0) {
                                            Badge {
                                                Text(text = "${state.cartItemSize}")
                                            }
                                        }
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (state.selectedBottomTopIndex == index) bottomNavItem.selectedIcon
                                    else bottomNavItem.unselectedIcon,
                                    contentDescription = null
                                )
                            }
                        },
                        label = {
                            Text(text = bottomNavItem.title)
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        HomeContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            allProducts = state.allProducts,
            selectedCategoryItems = state.singleCategory,
            onCategorySelected = { category ->
                onEvent(HomeEvent.OnCategoryClick(category))
            },
            onProductClick = { product ->
                onEvent(HomeEvent.OnProductClick(product.id))
            },
            selectedTab = state.selectedTopTabIndex,
            isLoading = state.isLoading,
            onTabSelected = { index ->
                onEvent(HomeEvent.OnTabSelected(index))
            }
        )
    }
}