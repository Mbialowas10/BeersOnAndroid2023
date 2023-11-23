package com.mbialowas.beeronandroid2023demo.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.mbialowas.beeronandroid2023demo.R
import com.mbialowas.beeronandroid2023demo.api.BeersManager
import com.mbialowas.beeronandroid2023demo.model.BeerItem
import java.math.BigDecimal
import java.math.RoundingMode


@Composable
fun Beers(beersManager: BeersManager) {
    //BeerCard()
    val beers = beersManager.beersResponse.value

    //Log.i("API_Response", beersManager.beersResponse.value.toString())

    LazyColumn{
        items(beers){beer ->
            BeerCard(beerItem = beer)
        }
    }
}

@Composable
fun BeerCard (beerItem: BeerItem){
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
                //painter = painterResource(id = R.drawable.ic_launcher_background),
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
                Column {

                    Row{
                        Text(
                            text = "Average Vote: " + BigDecimal(beerItem.rating.average).setScale(2,RoundingMode.HALF_UP).toString() + "/5",
                            modifier = Modifier.padding(end = 8.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                        Text(
                            text = "# of Reviews " + beerItem.rating.toString(),
                            modifier = Modifier.padding(end = 8.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                    }
                }

            }
        }
    }
}