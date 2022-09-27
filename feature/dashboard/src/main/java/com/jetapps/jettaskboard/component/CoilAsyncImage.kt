package com.jetapps.jettaskboard.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jetapps.jettaskboard.feature.dashboard.R

@Composable
fun CoilAsyncImage(
    imageUrl: String = "https://www.mindinventory.com/blog/wp-content/uploads/2018/05/android-jetpack.jpg",
    imageShape: Shape,
    imageSize: Dp
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.temp_place_holder),
        contentDescription = stringResource(R.string.content_description_for_place_holder),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(shape = imageShape)
            .size(imageSize)
    )

}