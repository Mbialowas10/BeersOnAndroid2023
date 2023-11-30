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
import com.mbialowas.beeronandroid2023demo.model.BeerItem
import com.mbialowas.beeronandroid2023demo.navigation.BottomNavItem
import com.mbialowas.db.FireStoreInstance

@Composable
fun FavoriteScreen(){

    // mutable state to hold this list of beer items fetched from Firestore
    val fsInstance = FireStoreInstance.getInstance()
    val beerItems = remember{ mutableStateOf(listOf<BeerItem>())}

    Text(text="Likes Screen")

    // fetch beer from FireStore
    fun fetchBeerItems(){
        fsInstance.collection("favorites")
            .get()
            .addOnSuccessListener { documents->
                val items = mutableListOf<BeerItem>()
                for (document in documents){
                    // convert Firestore docuemnt to BeerItem model
                    val beerItem = document.toObject(BeerItem::class.java)
                    items.add(beerItem)
                }
                // updae the mutable state with fetched beer items
                beerItems.value = items
            }
            .addOnFailureListener{
                exception ->

                exception.message?.let { Log.d("MJB_Exception", it) }
            }
    }
    // fetch beer items when this composable get launched
    LaunchedEffect(Unit){
        fetchBeerItems()
    }
    // Display fetched items in LazyColumn
    LazyColumn(modifier = Modifier.padding(8.dp)){
        items(beerItems.value){
            beerItems->
            BeerCard(beerItem = beerItems)
        }
    }

}