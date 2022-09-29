package com.jetapps.jettaskboard.notifications.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun NotificationMenuBar(onCancelClick: () -> Unit) {
    TopAppBar(
        title = { Text("Notifications") },
        navigationIcon = {
            IconButton(onClick = onCancelClick) {
                Icon(Icons.Filled.Clear, "Cancel")
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }, enabled = true) {
                Icon(imageVector = Icons.Outlined.AddCircle, contentDescription = null)
            }
            IconButton(onClick = { /*TODO*/ }, enabled = true) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
            }
        }, backgroundColor = Color.Black, contentColor = Color.White
    )
}