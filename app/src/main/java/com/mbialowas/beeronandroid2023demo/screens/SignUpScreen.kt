package com.mbialowas.beeronandroid2023demo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: navController){

    // state level variables ie. to hold our state
    var email by remember { mutableStateOf("")}
    var password by remember { mutableStateOf("")}
    var errorMessage by remember { mutableStateOf("")}
    var auth: FirebaseAuth = Firebase.auth
    var TAG = "MJB"

    Column{
        // Email and pw input fields
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement=Arrangement.Center
        ){
            // Email input field
            TextField(
                value = email,
                onValueChange = {email = it.trim()},
                label = {Text("Email")},
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier=Modifier.height(16.dp))

            // Password field
            TextField(
                value = password,
                onValueChange = {
                    password = it.trim()
                },
                label = { Text(text = "Password")},
                modifier = Modifier.fillMaxSize(),
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))


        }
    }


}