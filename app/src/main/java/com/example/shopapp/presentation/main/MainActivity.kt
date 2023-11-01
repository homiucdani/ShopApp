package com.example.shopapp.presentation.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.shopapp.navigation.Screen
import com.example.shopapp.navigation.SetupNavGraph
import com.example.shopapp.navigation.listOfNavItems
import com.example.shopapp.ui.theme.ShopAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ShopAppTheme {
                val navController = rememberNavController()

                val mainViewModel: MainActivityViewModel = hiltViewModel()
                val state = mainViewModel.state.collectAsState().value

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            listOfNavItems.forEachIndexed { index, bottomNavItem ->
                                NavigationBarItem(
                                    selected = state.currentSelectedTab == index,
                                    onClick = {
                                        mainViewModel.onEvent(
                                            MainActivityEvent.SelectedBottomTab(
                                                index
                                            )
                                        )
                                        navController.navigate(bottomNavItem.route)
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
                                                imageVector = if (state.currentSelectedTab == index) bottomNavItem.selectedIcon
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
                    SetupNavGraph(
                        navHostController = navController
                    )
                }
            }
        }
    }
}

