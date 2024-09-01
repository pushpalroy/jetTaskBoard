package com.jetapps.jettaskboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.component.Header
import com.jetapps.jettaskboard.components.WorkshopCard
import com.jetapps.jettaskboard.model.Board
import com.jetapps.jettaskboard.model.BoardModel

@Composable
fun DashboardDetailPane(
    navigateToTaskBoard: (String) -> Unit,
    boardList: List<BoardModel>,
    createCardEvent: () -> Unit,
) {
    Column {
        Header(
            modifier = Modifier,
            title = "Trello workspace",
            showIcon = true
        )
        if (boardList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Button(
                    onClick = createCardEvent
                ) {
                    Text(text = "Create a new Card")
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 4.dp)
            ) {
                items(boardList) {
                    WorkshopCard(
                        modifier = Modifier.clickable { navigateToTaskBoard(it.id.toString()) },
                        title = it.title,
                        imageUrl = it.imageUrl ?: "",
                        isWorkshopStarred = it.isFav
                    )
                }
            }
        }
    }
}