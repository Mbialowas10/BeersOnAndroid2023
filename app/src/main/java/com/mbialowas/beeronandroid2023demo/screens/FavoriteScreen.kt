package com.mbialowas.beeronandroid2023demo.screens

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mbialowas.beeronandroid2023demo.api.BeersManager
import com.mbialowas.beeronandroid2023demo.model.BeerItem
import com.mbialowas.db.FireStoreInstance

@Composable
fun FavoriteScreen(beersManager: BeersManager, navController: NavController){

    // mutable state to hold the list of beer items fetched from Firestore
    val beerItems = remember { mutableStateOf( listOf<BeerItem>()) }
    val fsInstance = FireStoreInstance.getInstance()

    Text(text="Likes Screen")

    // function to fetch beer item from Firestore collection
    fun fetchBeerItems(){
        fsInstance.collection("favorites")
            .get()
            .addOnSuccessListener { documents ->
                val items = mutableListOf<BeerItem>()
                for (document in documents){
                    // convert Firestore document to BeerItem model
                    val beerItem = document.toObject(BeerItem::class.java)
                    Log.i("MJB Logged", beerItem.name.toString())
                    items.add(beerItem)
                }
                //update the mutaable state with fetch beer items
                beerItems.value = items

            }
            .addOnFailureListener{
                e ->
                Log.d("MJB Error", e.message.toString() )
            }
    }
    // fetch beer itesm when the composable get launched
    LaunchedEffect(Unit){
        fetchBeerItems()
    }

    // display fetch beer item in  lazycoloumn
    LazyColumn(modifier=  Modifier.padding(8.dp)){
        items(beerItems.value){ beerItem ->
            BeerCard(beerItem = beerItem, navController)

        }
    }
}