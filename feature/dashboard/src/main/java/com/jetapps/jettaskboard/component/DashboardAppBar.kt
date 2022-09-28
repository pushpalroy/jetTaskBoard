package com.jetapps.jettaskboard.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable

/**
 * Todo: Check if `actions` is used as per best practices
 * Todo: Add Small animation to the Search and Notification
 */
@Composable
fun DashboardAppBar(
    onMenuIconClick: () -> Unit,
    onSearchIconClicked: () -> Unit,
    onNotificationIconClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "Boards")
        },
        navigationIcon = {
            IconButton(onClick = onMenuIconClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Drawer"
                )
            }
        },
        actions = {
            IconButton(onClick = onSearchIconClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }

            IconButton(onClick = onNotificationIconClicked) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notification"
                )
            }
        }
    )
}