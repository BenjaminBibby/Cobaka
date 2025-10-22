package com.example.cobaka.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Dogs : Screen(
        route = "dogs",
        title = "Dogs",
        icon = Icons.Default.Home
    )

    object FavouriteDogs : Screen(
        route = "favourite_dogs",
        title = "Favourite Dogs",
        icon = Icons.Default.Favorite
    )
}
