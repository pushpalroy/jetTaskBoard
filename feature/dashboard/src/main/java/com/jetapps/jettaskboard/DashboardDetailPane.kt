package com.jetapps.jettaskboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.component.Header
import com.jetapps.jettaskboard.components.WorkshopCard

@Composable
fun DashboardDetailPane(
    viewModel: DashboardViewModel,
    navigateToTaskBoard: (String) -> Unit
) {
    Column {
        Header(
            modifier = Modifier,
            title = "Trello workspace",
            showIcon = true
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 4.dp)
        ) {
            items(viewModel.listOfBoards) {
                WorkshopCard(
                    modifier = Modifier.clickable { navigateToTaskBoard("") },
                    title = it.title,
                    imageUrl = it.imageUrl,
                    isWorkshopStarred = it.isStarred
                )
            }
        }
    }
}