package com.mbialowas.beeronandroid2023demo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mbialowas.beeronandroid2023demo.R

@Composable
fun BeerCard(

){
    Row(
        modifier = Modifier
            .background(color = Color.DarkGray)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter= painterResource(id = R.drawable.bluemoon),
            contentDescription = "Blue Moon"
        )
        Column {
            Text(
                text = "Blue Moon",
                modifier = Modifier.padding(end = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }

    }
}
