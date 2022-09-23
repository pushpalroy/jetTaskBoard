package com.jetapps.jettaskboard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(
    modifier: Modifier,
    title: String,
    showIcon: Boolean = false,
    icon: ImageVector = Icons.Default.Menu,
    onMenuItemClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Black.copy(alpha = 0.75f)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            modifier = modifier
                .padding(16.dp)
                .weight(1f),
            text = title,
            textAlign = TextAlign.Start,
            fontStyle = FontStyle.Normal,
            fontSize = 18.sp,
            color = Color.White
        )
        if (showIcon) {
            IconButton(
                onClick = { onMenuItemClicked() },
                modifier = modifier
                    .align(CenterVertically)
            ) {
                Icon(
                    imageVector = icon,
                    tint = Color.White,
                    contentDescription = "Menu Icon"
                )
            }
        }
    }
}