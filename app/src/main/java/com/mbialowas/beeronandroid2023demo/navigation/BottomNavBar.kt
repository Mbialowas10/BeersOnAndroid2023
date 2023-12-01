package com.mbialowas.beeronandroid2023demo.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mbialowas.beeronandroid2023demo.navigation.BottomNavItem.About.icon


@Composable
fun BottomNavBar(navController: NavController){
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favorite,
        BottomNavItem.About
    )
    NavigationBar(
        modifier = Modifier.navigationBarsPadding(),
        containerColor = MaterialTheme.colorScheme.background
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        // loop through navitems to wire up our navigation system
        items.forEach { item ->
            NavigationBarItem (
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route){
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        // avoid multiple copies of the same destination
                        launchSingleTop = true
                        // restore state when reselection of a previously selected item
                        restoreState = true
                    }
                },
                    icon ={
                        Icon(
                            imageVector = item.icon,
                            contentDescription = "Always a good idea to fill for screen readers"
                        )
                }
            )
        }

    }
}