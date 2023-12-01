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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mbialowas.beeronandroid2023demo.api.BeersManager
import com.mbialowas.beeronandroid2023demo.navigation.BottomNavBar
import com.mbialowas.beeronandroid2023demo.navigation.BottomNavItem
import com.mbialowas.beeronandroid2023demo.screens.About
import com.mbialowas.beeronandroid2023demo.screens.BeerCard
import com.mbialowas.beeronandroid2023demo.screens.Beers
import com.mbialowas.beeronandroid2023demo.screens.FavoriteScreen
import com.mbialowas.beeronandroid2023demo.ui.theme.BeerOnAndroid2023DemoTheme

class MainActivity : ComponentActivity() {

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


                    // fetch our beers from the api
                    val beersManager: BeersManager = BeersManager()

                    //Beers(beersManager)

                    val navController = rememberNavController()

                    Scaffold(
                        bottomBar = {BottomNavBar(navController)}
                    ){
                        NavHost(navController, startDestination = BottomNavItem.Home.route){
                            composable(BottomNavItem.Home.route){
                                Beers(beersManager = beersManager, navController)
                            }
                            // enable the navigation to FavoriteScreen() composable
                            composable(BottomNavItem.Favorite.route){
                                FavoriteScreen()
                            }
                            composable(BottomNavItem.About.route){
                                About(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

