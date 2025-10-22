package com.example.cobaka.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val navItems = listOf(
        Screen.Dogs,
        Screen.FavouriteDogs
    )

    Scaffold(bottomBar = {
        NavigationBar {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            navItems.forEach { screen ->
                NavigationBarItem(
                    label = { Text(text = screen.title) },
                    icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                    selected = currentDestination?.route == screen.route,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dogs.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Dogs.route) {
                DogScreen()
            }
            composable(Screen.FavouriteDogs.route) {
                FavouriteDogsScreen()
            }
        }
    }
}