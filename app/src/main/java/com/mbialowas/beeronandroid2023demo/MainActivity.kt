package com.mbialowas.beeronandroid2023demo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth


import com.mbialowas.beeronandroid2023demo.api.BeersManager
import com.mbialowas.beeronandroid2023demo.navigation.BottomNavBar
import com.mbialowas.beeronandroid2023demo.navigation.BottomNavItem
import com.mbialowas.beeronandroid2023demo.screens.About
import com.mbialowas.beeronandroid2023demo.screens.Beers
import com.mbialowas.beeronandroid2023demo.screens.FavoriteScreen
import com.mbialowas.beeronandroid2023demo.ui.theme.BeerOnAndroid2023DemoTheme

class MainActivity : ComponentActivity() {

    private var auth: FirebaseAuth? = null

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeerOnAndroid2023DemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // fetch api data when class is initialized
                    val beersManager:BeersManager = BeersManager()

                    ///Beers(beersManager)

                    val navController = rememberNavController()

                    val user = auth?.currentUser

                    if ( user == null ){
                        navController.navigate("login")
                    }

                    Scaffold (
                        bottomBar = { BottomNavBar(navController)}
                    ){
                        NavHost(navController = navController, startDestination = BottomNavItem.Home.route ){
                            composable(BottomNavItem.Home.route){
                                Beers(beersManager= beersManager, navController)
                            }
                            composable(BottomNavItem.Favorite.route){
                                // todo - FavoriteScreen(beersManager=beersManager, navController)
                                FavoriteScreen(beersManager = beersManager, navController =  navController)
                            }
                            composable(BottomNavItem.About.route){
                                // todo - About(navController)
                                About(navController)
                            }

                        }

                    }
                }
            }
        }
    }
}

