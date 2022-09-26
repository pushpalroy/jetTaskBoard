package com.jetapps.jettaskboard.drawer

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.twotone.Email
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetapps.jettaskboard.DashboardViewModel
import com.jetapps.jettaskboard.component.NavigationDrawerItem

@Composable
fun JtbDrawer(
    modifier: Modifier,
    viewModel: DashboardViewModel
) {
    val scrollState: ScrollState = rememberScrollState()
    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
    ) {
        DrawerHeader(
            modifier = modifier,
            onDrawerHeaderToggled = {
                // Todo Manage Expandable State
            }
        )

        Divider()

        Row(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Board Icon Desc",
            )

            Text(
                modifier = modifier
                    .weight(1f)
                    .padding(start = 24.dp),
                text = "Boards",
                fontWeight = FontWeight.Light,
                fontSize = 18.sp
            )
        }
        Divider()
        DrawerWorkSpaceComponent(
            viewModel = viewModel,
            modifier = modifier
        )

        Divider()
        NavigationDrawerItem(
            modifier = modifier,
            heading = "My Card",
            icon = Icons.TwoTone.Email,
        ) {}
        NavigationDrawerItem(
            modifier = modifier,
            heading = "Settings",
            icon = Icons.TwoTone.Settings
        ) {}
        NavigationDrawerItem(
            modifier = modifier,
            heading = "Help!",
            icon = Icons.TwoTone.Info
        ) {}
    }
}