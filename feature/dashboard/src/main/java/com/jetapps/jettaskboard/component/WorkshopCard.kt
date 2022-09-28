package com.jetapps.jettaskboard.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            .padding(vertical = 6.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CoilAsyncImage(
            imageUrl = imageUrl,
            imageShape = RoundedCornerShape(5),
            imageSize = 42.dp
        )

        Text(
            text = title,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f),
            fontSize = 14.sp
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