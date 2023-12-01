package com.mbialowas.beeronandroid2023demo.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector

){
  object Home: BottomNavItem(
      "home","Home", Icons.Default.Home
  )
    object Favorite : BottomNavItem(
        "favorite", "Farvoite", Icons.Default.Favorite
    )
    object About : BottomNavItem(
        "about", "About", Icons.Default.Info
    )
}