package com.jetapps.search.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.components.BoardCardComponent
import com.jetapps.jettaskboard.model.Board

@Composable
fun LazyVerticalGridBoardList(
    boardList: List<Board>,
    navigateToTaskBoard: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .padding(top = 4.dp, bottom = 8.dp)
            .fillMaxSize(),
        columns = GridCells.Adaptive(minSize = 150.dp),
        contentPadding = PaddingValues(4.dp),
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