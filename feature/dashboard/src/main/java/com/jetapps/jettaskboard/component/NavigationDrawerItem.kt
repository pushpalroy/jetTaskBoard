package com.jetapps.jettaskboard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NavigationDrawerItem(
    modifier: Modifier,
    heading: String,
    icon: ImageVector,
    onNavigationDrawerItemClicked: () -> Unit
) {
    Surface(
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .clickable {
                onNavigationDrawerItemClicked()
            }
    ) {
        Row(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(imageVector = icon, contentDescription = "navDrawer Item $heading")

            Text(
                modifier = modifier.padding(start = 24.dp),
                text = heading,
                fontSize = 18.sp,
                fontStyle = FontStyle.Normal
            )
        }
    }
}