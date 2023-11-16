package com.mbialowas.beeronandroid2023demo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.mbialowas.beeronandroid2023demo.R


@Composable
fun Beers() {
    BeerCard()
}

@Composable
fun BeerCard (){
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
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Beer"
            )
            Column {
                Text(
                    color = Color.White,
                    text = "A Beer ....",
                    modifier = Modifier
                        .padding(top=8.dp,end=8.dp),
                    style= TextStyle(fontSize= 24.sp),
                    maxLines=1
                )
                Text(
                    text= "2.33",
                    modifier = Modifier.padding(end=8.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White

                )
            }
        }
    }
}