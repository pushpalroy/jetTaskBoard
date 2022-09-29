package com.jetapps.jettaskboard.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetapps.jettaskboard.DashboardViewModel
import com.jetapps.jettaskboard.component.NavigationDrawerItem

@Composable
fun JtbDrawer(
    modifier: Modifier,
    viewModel: DashboardViewModel,
    isExpandedScreen: Boolean = false,
    isMenuClickedInExpandedMode: Boolean = false
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .verticalScroll(scrollState)
            .fillMaxHeight()
            .background(color = Color(0xFF2c2c2e))
    ) {
        if (isExpandedScreen.not()) {
            OpenDrawerInExpandedMode(
                Modifier, viewModel
            )
        } else {
            if (isMenuClickedInExpandedMode.not()) {
                OpenDrawerInExpandedMode(
                    Modifier, viewModel
                )
            } else {
                ClosedDrawerInExpandedMode(
                    Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun OpenDrawerInExpandedMode(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel,
) {
    Column(
        modifier = Modifier
    ) {
        DrawerHeader(
            modifier = modifier,
            onDrawerHeaderToggled = {
                // Todo Manage Expandable State
            }
        )

        Divider(
            color = Color(0xFF1B1B1D)
        )
        Row(
            modifier = modifier
                .background(color = MaterialTheme.colors.background)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Home,
                contentDescription = "Board Icon Desc",
                tint = Color(0xFF0079bf)
            )
            Text(
                modifier = modifier
                    .weight(1f)
                    .padding(start = 24.dp),
                color = Color(0xFF0079bf),
                text = "Boards",
                fontSize = 16.sp
            )
        }
        Divider(
            color = Color(0xFF1B1B1D)
        )
        DrawerWorkSpaceComponent(
            viewModel = viewModel,
            modifier = modifier
        )

        Divider(
            color = Color(0xFF1B1B1D)
        )
        NavigationDrawerItem(
            modifier = modifier,
            heading = "My cards",
            icon = Icons.Outlined.Email,
        ) {}
        NavigationDrawerItem(
            modifier = modifier,
            heading = "Settings",
            icon = Icons.Outlined.Settings
        ) {}
        NavigationDrawerItem(
            modifier = modifier,
            heading = "Help!",
            icon = Icons.Outlined.Info
        ) {}
    }
}

@Composable
fun ClosedDrawerInExpandedMode(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.wrapContentHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.padding(16.dp),
            imageVector = Icons.Outlined.Email,
            contentDescription = null
        )
        Icon(
            modifier = Modifier.padding(16.dp),
            imageVector = Icons.Outlined.Settings,
            contentDescription = null
        )
        Icon(
            modifier = Modifier.padding(16.dp),
            imageVector = Icons.Outlined.Info,
            contentDescription = null
        )
    }
}