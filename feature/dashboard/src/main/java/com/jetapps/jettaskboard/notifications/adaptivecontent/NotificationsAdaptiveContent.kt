package com.jetapps.jettaskboard.notifications.adaptivecontent

import androidx.compose.runtime.Composable

@Composable
fun NotificationsAdaptiveContent(
    isScreenExpanded : Boolean = false,
    navigateUp : () -> Unit,
) {

    val filterChips = listOf("Me", "Unread", "Comments", "Requests")

    if (!isScreenExpanded){
        NotificationsSinglePaneContent(filterChips)
    }else{
        NotificationsDoublePaneContent(filterChips)
    }
}