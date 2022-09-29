package com.jetapps.jettaskboard.notifications.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainNotificationScreen(onCancelClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(
            topBar = {
                NotificationMenuBar(onCancelClick)
            },
            content = { padding -> NotificationSurface(padding = padding) }
        )
    }
}