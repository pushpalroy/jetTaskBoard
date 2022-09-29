package com.jetapps.jettaskboard.notifications

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jetapps.jettaskboard.notifications.components.MainNotificationScreen

@Composable
fun NotificationsRoute(
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    MainNotificationScreen(onCancelClick)
}