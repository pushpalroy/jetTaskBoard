package com.jetapps.jettaskboard.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.jetapps.jettaskboard.loadingcomponents.TrelloNotificationAnimationIcon

@Composable
fun DashboardAppBar(
    isExpandedScreen: Boolean,
    onMenuIconClick: () -> Unit,
    onSearchIconClicked: () -> Unit,
    onNotificationIconClicked: () -> Unit,
    notificationCount: Int = 0
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
        backgroundColor = Color(0xFF3f3f41),
        actions = {
            IconButton(onClick = onSearchIconClicked) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search"
                )
            }

            IconButton(onClick = onNotificationIconClicked) {
                TrelloNotificationAnimationIcon(
                    notificationCounts = 11
                )
            }
        }
    )
}
