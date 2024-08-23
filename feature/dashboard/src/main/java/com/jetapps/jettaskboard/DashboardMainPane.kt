package com.jetapps.jettaskboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells.Adaptive
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.component.Header
import com.jetapps.jettaskboard.components.BoardCardComponent
import com.jetapps.jettaskboard.components.WorkshopCard
import com.jetapps.jettaskboard.model.Board

@Composable
fun DashboardMainPaneContent(
    isExpanded: Boolean,
    boardList: List<Board>,
    navigateToTaskBoard: (String) -> Unit = {},
    createBoard: () -> Unit,
) {
    if (isExpanded) {
        DashboardMainPaneAtExpandedScreen(
            boardList = boardList,
            navigateToTaskBoard
        )
    } else {
        DashboardMainPaneScreen(
            boardList = boardList,
            navigateToTaskBoard = navigateToTaskBoard,
            createBoard = createBoard
        )
    }
}

@Composable
fun DashboardMainPaneAtExpandedScreen(
    boardList: List<Board>,
    navigateToTaskBoard: (String) -> Unit
) {
    Column {
        Header(
            modifier = Modifier,
            title = "Starred Boards",
            onMenuItemClicked = {}
        )
        Row {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 8.dp, end = 8.dp)
                    .fillMaxHeight()
                    .fillMaxWidth(0.999f),
                columns = Fixed(1),
                contentPadding = PaddingValues(4.dp)
            ) {
                if (boardList.isNotEmpty()) {
                    items(boardList) { boardItem ->
                        BoardCardComponent(
                            modifier = Modifier.clickable { navigateToTaskBoard("") },
                            title = boardItem.title,
                            backgroundImageUrl = boardItem.imageUrl
                        )
                    }
                }
            }
            Divider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxHeight()
                    .width(1.dp)
            )
        }
    }
}

@Composable
fun DashboardMainPaneScreen(
    boardList: List<Board>,
    createBoard: () -> Unit,
    navigateToTaskBoard: (String) -> Unit = {}
) {
    if (boardList.isEmpty()) {
        Box(
            modifier = Modifier.wrapContentSize(),
            contentAlignment = Alignment.Center,
        ) {
            Button(onClick = createBoard) {
                Text(text = "Create a new Board")
            }
        }
    } else {
        LazyColumn {
            item {
                Header(
                    modifier = Modifier,
                    title = "Starred Boards",
                    onMenuItemClicked = {}
                )
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 8.dp)
                        .height(240.dp),
                    columns = Adaptive(minSize = 150.dp),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    if (boardList.isNotEmpty()) {
                        items(boardList.subList(0, 5)) { boardItem ->
                            BoardCardComponent(
                                modifier = Modifier.clickable { navigateToTaskBoard("") },
                                title = boardItem.title,
                                backgroundImageUrl = boardItem.imageUrl
                            )
                        }
                    }
                }
            }
            item {
                Header(
                    modifier = Modifier,
                    title = "Trello workspace",
                    showIcon = true
                )
                LazyColumn(
                    modifier = Modifier
                        .height(320.dp)
                        .padding(top = 4.dp)
                ) {
                    items(boardList) {
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
    }
}
