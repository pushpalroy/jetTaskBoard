package com.jetapps.jettaskboard.board

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.jetapps.jettaskboard.feature.taskboard.R

@Composable
fun TaskBoardExpandedScreenDrawer(
    navigateToChangeBackgroundRoute: (String) -> Unit,
    navigateToFilterRoute: () -> Unit = {},
    navigateToAutomationRoute: () -> Unit = {},
    navigateToPowerUpRoute: () -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Change Background Item
        TaskBoardExpandedScreenDrawerItem(
            title = "Change Background",
            icon = painterResource(id = R.drawable.ic_baseline_wallpaper_24),
            onItemClickListener = navigateToChangeBackgroundRoute
        )

        // Filter Item
        TaskBoardExpandedScreenDrawerItem(
            title = "Filter",
            icon = painterResource(id = R.drawable.ic_baseline_filter_list_24),
            onItemClickListener = navigateToChangeBackgroundRoute
        )

        // Automation Item
        TaskBoardExpandedScreenDrawerItem(
            title = "Automation",
            icon = painterResource(id = R.drawable.ic_baseline_automation_icon),
            onItemClickListener = navigateToChangeBackgroundRoute
        )

        // Power's-up Item
        TaskBoardExpandedScreenDrawerItem(
            title = "Power's-up",
            icon = painterResource(id = R.drawable.ic_baseline_circle_notifications_24),
            onItemClickListener = navigateToChangeBackgroundRoute
        )
    }
}