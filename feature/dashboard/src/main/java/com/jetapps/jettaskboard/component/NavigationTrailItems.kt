package com.jetapps.jettaskboard.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavigationTrailItems(val title: String, val icon: ImageVector) {
    HOME("Message", Icons.Default.Email),
    SETTINGS("Settings", Icons.Default.Settings),
    SEARCH("Info", Icons.Default.Info),
}