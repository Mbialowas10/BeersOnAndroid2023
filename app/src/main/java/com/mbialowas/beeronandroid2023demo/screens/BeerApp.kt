package com.mbialowas.beeronandroid2023demo.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mbialowas.beeronandroid2023demo.api.BeersManager


@Composable
fun BeerApp(beersManager: BeersManager){
    Navigation(beersManager)
}
@Composable
fun Navigation(beersManager: BeersManager){
    val navController = rememberNavController()

    NavHost(
        navController= navController,
        startDestination = "beers"
    ){
        composable("beers"){
            Beers(beersManager,navController)
        }
        composable("about"){
            About(navController)
        }
    }
}