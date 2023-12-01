package com.mbialowas.beeronandroid2023demo.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun About(
    navController: NavController
){
    Column(){
        Text(text = "About")
    }
}