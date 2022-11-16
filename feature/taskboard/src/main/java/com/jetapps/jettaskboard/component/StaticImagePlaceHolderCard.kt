package com.jetapps.jettaskboard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.feature.taskboard.R

@Composable
fun StaticImagePlaceHolderCard(
    modifier: Modifier = Modifier,
    onImageClicked: (type: String) -> Unit,
    subTitle: String
) {
    Card(
        modifier = modifier
            .background(Color.Transparent)
            .clickable {
            onImageClicked(subTitle)
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.bg_board),
                contentDescription = "background",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = subTitle)
        }
    }
}