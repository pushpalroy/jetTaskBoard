package com.jetapps.jettaskboard.notifications.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NotificationSurface(padding: PaddingValues) {
    val filterChips = listOf("Me", "Unread", "Comments", "Requests")


    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SelectableFilters(filterChips)
        ImageFilled()
        MandatoryText()
    }
}