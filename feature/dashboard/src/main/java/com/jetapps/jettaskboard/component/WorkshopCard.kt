package com.jetapps.jettaskboard.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jetapps.jettaskboard.feature.dashboard.R

@Composable
fun WorkshopCard(
    modifier: Modifier,
    title: String,
    imageUrl: String,
    isWorkshopStarred: Boolean = false,
    starPainterResource: ImageVector = Icons.Outlined.Star
) {
    Row(
        modifier = modifier
            .padding(all = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CoilAsyncImage(
            imageUrl = imageUrl,
            imageShape = RoundedCornerShape(8.dp),
            imageSize = 64.dp
        )

        Text(
            text = title,
            style = MaterialTheme.typography.caption,
            modifier = modifier
                .padding(start = 24.dp)
                .weight(1f),
            fontSize = 18.sp
        )

        if (isWorkshopStarred) {
            Icon(
                imageVector = starPainterResource,
                modifier = modifier.size(24.dp),
                contentDescription = "star",
                tint = Color.Yellow
            )
        }
    }
}