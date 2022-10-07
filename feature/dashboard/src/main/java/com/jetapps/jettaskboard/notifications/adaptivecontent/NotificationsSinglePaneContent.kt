package com.jetapps.jettaskboard.notifications.adaptivecontent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.notifications.components.ImageFilled
import com.jetapps.jettaskboard.notifications.components.MandatoryText
import com.jetapps.jettaskboard.notifications.components.SelectableFilters

@Composable
fun NotificationsSinglePaneContent(filterChips: List<String>) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.onBackground)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SelectableFilters(filterChips)
        ImageFilled()
        MandatoryText()
    }
}


@Preview
@Composable
fun NotificatonsPrev() {
    NotificationsSinglePaneContent(filterChips = listOf("Me", "Unread", "Comments", "Requests"))
}