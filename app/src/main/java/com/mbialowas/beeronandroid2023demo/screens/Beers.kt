package com.mbialowas.beeronandroid2023demo.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.mbialowas.beeronandroid2023demo.R
import com.mbialowas.beeronandroid2023demo.api.BeersManager
import com.mbialowas.beeronandroid2023demo.model.BeerItem
import com.mbialowas.db.FireStoreInstance
import java.math.BigDecimal
import java.math.RoundingMode

// TODO - is navController required here?
@Composable
fun Beers(beersManager: BeersManager, navController: NavController) {
    val beers = beersManager.beersResponse.value
    Log.i("DataResponse", "$beers")

    LazyColumn{
        items(beers){beer->
            BeerCard(beerItem = beer)
        }
    }


}

@Composable
fun BeerCard ( beerItem: BeerItem){

    // state level variables
    var isIconChanged by remember { mutableStateOf(false)}
    val fsInstancee = FireStoreInstance.getInstance()

    // parameters
    Column(
        modifier = Modifier
            .border(1.dp, Color.Red, shape = RectangleShape)
            .padding(5.dp)
    ) {
        Row(
          modifier = Modifier
              .background(color = Color.DarkGray)
              .fillMaxWidth()
              .padding(5.dp)
        ){
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                painter = rememberImagePainter(data = beerItem.image),
                contentDescription = "Beer"
            )
            Column {
                Text(
                    color = Color.White,
                    text = beerItem.name,
                    modifier = Modifier
                        .padding(top=8.dp,end=8.dp),
                    style= TextStyle(fontSize= 24.sp),
                    maxLines=1
                )
                Text(
                    text= beerItem.price,
                    modifier = Modifier.padding(end=8.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White

                )
                Column{
                    Row{
                        Text(
                          text="Average Vote: " + BigDecimal(beerItem.rating.average).setScale(2,
                              RoundingMode.HALF_UP).toString() + "/5",
                            modifier = Modifier
                                .padding(end = 8.dp),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White
                        )
                        Text(
                            text = "# of Reviews " + beerItem.rating.reviews.toString(),
                            modifier = Modifier.padding(end=8.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                        Button(
                            onClick = {
                               isIconChanged = !isIconChanged

                               // setup and write to our firestore collection
                                val beerDocRec = fsInstancee.collection("favorites").document(beerItem.id.toString())

                                if (isIconChanged){
                                    beerDocRec.set(beerItem)
                                        .addOnSuccessListener {
                                            Log.d("MJB", "Inserted ${beerItem.name}")
                                        }
                                        .addOnFailureListener{
                                            e->
                                            Log.d("Error", "${e.message}")
                                        }
                                }else{
                                    beerDocRec.delete()
                                        .addOnSuccessListener {
                                            Log.d("MJB", "Deleted ${beerItem.name}")
                                        }
                                        .addOnFailureListener{
                                            e->
                                            Log.d("Error", "${e.message}")
                                        }
                                }
                            } // closing onlick
                        ){
                            Icon(
                                modifier = Modifier
                                    .size(24.dp)
                                    .scale(2.5f),
                                imageVector = if (isIconChanged) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Add A favorite"
                            )
                        }// close button composable
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}