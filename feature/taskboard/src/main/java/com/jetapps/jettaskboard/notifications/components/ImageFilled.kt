package com.jetapps.jettaskboard.notifications.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ImageFilled() {
    Icon(
        imageVector = Icons.Rounded.Build,
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize(0.75f)
            .background(color = Color.White)
    )
}