package com.mbialowas.beeronandroid2023demo.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mbialowas.beeronandroid2023demo.Navigation.BottomNavItem


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
        // TODO - comeback one objects created
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                          navController.navigate(item.route){
                              popUpTo(navController.graph.startDestinationId){
                                  saveState = true
                              }
                              // avoid multiple copies of same destination
                              launchSingleTop = true
                              //restore state when reselecting a previously selected item
                              restoreState = true
                          }

                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "A image detailing navigation item"
                    )
                },
                label={
                    Text(text =  item.title)
                })
        }
    }
}