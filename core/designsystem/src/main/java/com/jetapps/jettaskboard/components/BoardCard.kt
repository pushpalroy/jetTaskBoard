package com.jetapps.jettaskboard.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jetapps.jettaskboard.core.designsystem.R

@Composable
fun BoardCardComponent(
    title: String,
    modifier: Modifier = Modifier,
    height: Dp = 120.dp,
    backgroundImageUrl: String,
    placeHolderImage: Painter = painterResource(R.drawable.temp_place_holder)
) {
    // Todo : Remove Card : (Review)
    Card(
        modifier = modifier
            .height(height)
            .padding(4.dp),
        shape = RoundedCornerShape(5)
    ) {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(backgroundImageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = placeHolderImage,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Surface(
                modifier = modifier
                    .fillMaxWidth()
                    .height(height.div(3))
                    .align(Alignment.BottomCenter),
                color = Color.Black.copy(alpha = 0.4f)
            ) {
                Text(
                    modifier = modifier
                        .padding(12.dp),
                    text = title,
                    fontStyle = FontStyle.Normal,
                    fontSize = 12.sp
                )
            }
        }
    }
}
