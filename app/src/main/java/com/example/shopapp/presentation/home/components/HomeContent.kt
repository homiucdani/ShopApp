package com.example.shopapp.presentation.home.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shopapp.domain.model.Product

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    allProducts: List<Product>,
    selectedCategoryItems: List<Product>,
    onCategorySelected: (String) -> Unit,
    onProductClick: (Product) -> Unit,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {

    val categories by remember {
        mutableStateOf(
            listOf(
                "All",
                "Electronics",
                "Jewelery",
                "Men's clothing",
                "Women's clothing"
            )
        )
    }

    LazyColumn(
        modifier = modifier
    ) {
        item {
            ScrollableTabRow(
                modifier = Modifier.fillMaxWidth(),
                selectedTabIndex = selectedTab,
                tabs = {
                    categories.forEachIndexed { index, category ->
                        Tab(
                            modifier = Modifier.padding(10.dp),
                            selected = selectedTab == index,
                            onClick = {
                                onTabSelected(index)
                                onCategorySelected(category.lowercase())
                            },
                            interactionSource = MutableInteractionSource()
                        ) {
                            Text(text = category)
                        }
                    }
                },
                edgePadding = 0.dp,
            )
        }

        when (selectedTab) {
            0 -> items(
                items = allProducts,
                key = {
                    it.id
                }
            ) { product ->
                CategoryContent(
                    product = product,
                    onProductClick = {
                        onProductClick(it)
                    }
                )
            }

            1 -> items(
                items = selectedCategoryItems,
                key = {
                    it.id
                }
            ) { product ->
                CategoryContent(
                    product = product,
                    onProductClick = {
                        onProductClick(it)
                    }
                )
            }

            2 -> items(
                items = selectedCategoryItems,
                key = {
                    it.id
                }
            ) { product ->
                CategoryContent(
                    product = product,
                    onProductClick = {
                        onProductClick(it)
                    }
                )
            }

            3 -> items(
                items = selectedCategoryItems,
                key = {
                    it.id
                }
            ) { product ->
                CategoryContent(
                    product = product,
                    onProductClick = {
                        onProductClick(it)
                    }
                )
            }

            4 -> items(
                items = selectedCategoryItems,
                key = {
                    it.id
                }
            ) { product ->
                CategoryContent(
                    product = product,
                    onProductClick = {
                        onProductClick(it)
                    }
                )
            }
        }
    }
}