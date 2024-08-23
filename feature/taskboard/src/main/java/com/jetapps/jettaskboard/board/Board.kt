package com.jetapps.jettaskboard.board

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetapps.jettaskboard.vm.TaskBoardViewModel
import com.jetapps.jettaskboard.components.TaskCard
import com.jetapps.jettaskboard.draganddrop.DragAndDropState
import com.jetapps.jettaskboard.draganddrop.DragAndDropSurface
import com.jetapps.jettaskboard.draganddrop.DragSurface
import com.jetapps.jettaskboard.draganddrop.DropSurface
import com.jetapps.jettaskboard.model.ListModel
import com.jetapps.jettaskboard.theme.SecondaryColor

@Composable
fun Board(
    modifier: Modifier = Modifier,
    navigateToCreateCard: (String) -> Unit = {},
    viewModel: TaskBoardViewModel,
    isExpandedScreen: Boolean
) {
    val boardState = remember { DragAndDropState(isExpandedScreen) }
    val boardList by viewModel.lists.collectAsState()

    LaunchedEffect(key1 = boardState.movingCardData) {
        if (boardState.hasCardMoved()) {
            viewModel.moveCardToDifferentList(
                cardId = boardState.movingCardData.first,
                oldListId = boardState.cardDraggedInitialListId,
                newListId = boardState.movingCardData.second
            )
        }
    }

    DragAndDropSurface(
        modifier = modifier.fillMaxSize(),
        state = boardState
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (boardList.isNotEmpty()) {
                items(boardList) { list ->
                    Lists(
                        boardState = boardState,
                        listModel = list,
                        isExpandedScreen = isExpandedScreen,
                        onTaskCardClick = navigateToCreateCard,
                        onAddCardClick = {
                            list.listId?.let { validId ->
                                viewModel.addNewCardInList(validId)
                            }
                        }
                    )
                }
            }
            item {
                AddNewListButton(
                    viewModel = viewModel,
                    isExpandedScreen = isExpandedScreen
                )
            }
        }
    }
}

@Composable
fun Lists(
    boardState: DragAndDropState,
    listModel: ListModel,
    onTaskCardClick: (String) -> Unit,
    onAddCardClick: () -> Unit,
    isExpandedScreen: Boolean
) {
    DropSurface(
        modifier = Modifier
            .padding(start = 16.dp, end = 0.dp, top = 16.dp, bottom = 8.dp)
            .background(
                color = Color(0xFF222222),
                shape = RoundedCornerShape(2)
            ),
        listId = listModel.listId ?: 0
    ) { isInBound, _ ->
        Column(
            modifier = Modifier
                .background(
                    color = getDropSurfaceBgColor(isInBound, boardState.isDragging)
                )
                .width(if (isExpandedScreen) 300.dp else 240.dp)
                .padding(if (isExpandedScreen) 8.dp else 4.dp)
        ) {
            ListHeader(
                name = listModel.title
            )
            ListBody(
                modifier = Modifier,
                listModel = listModel,
                onTaskCardClick = onTaskCardClick,
                onAddCardClick = onAddCardClick,
                isExpandedScreen = isExpandedScreen
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListBody(
    modifier: Modifier,
    listModel: ListModel,
    onTaskCardClick: (String) -> Unit,
    onAddCardClick: () -> Unit,
    isExpandedScreen: Boolean
) {
    LazyColumn(
        modifier = Modifier
    ) {
        items(listModel.cards) { card ->
            DragSurface(
                modifier = modifier
                    .fillMaxWidth()
                    .animateItemPlacement(),
                cardId = card.id,
                cardListId = card.listId ?: 0
            ) {
                TaskCard(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onTaskCardClick("1") },
                    card = card,
                    isExpandedScreen = isExpandedScreen
                )
            }
        }
        item {
            ListFooter(
                onAddCardClick = onAddCardClick
            )
        }
    }
}

@Composable
fun ListHeader(
    modifier: Modifier = Modifier,
    name: String
) {
    Row(
        modifier = modifier
            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 16.dp)
            .fillMaxWidth()
    ) {
        Text(modifier = Modifier.weight(1f), text = name)
        IconButton(modifier = Modifier.size(16.dp), onClick = {}) {
            Icon(imageVector = Filled.MoreVert, contentDescription = "Menu")
        }
    }
}

@Composable
fun ListFooter(
    modifier: Modifier = Modifier,
    onAddCardClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(start = 8.dp, top = 16.dp, end = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
    ) {
        TextButton(
            modifier = Modifier
                .height(24.dp),
            contentPadding = PaddingValues(4.dp),
            colors = ButtonDefaults.textButtonColors(
                contentColor = SecondaryColor
            ),
            onClick = { onAddCardClick() }
        ) {
            Icon(imageVector = Filled.Add, contentDescription = "Add")
            Text(modifier = Modifier, fontSize = 10.sp, text = "Add Card")
        }
    }
}

@Composable
fun AddNewListButton(
    viewModel: TaskBoardViewModel,
    isExpandedScreen: Boolean
) {
    TextButton(
        modifier = Modifier
            .padding(16.dp)
            .width(if (isExpandedScreen) 300.dp else 240.dp),
        colors = ButtonDefaults.textButtonColors(
            contentColor = Color.White,
            backgroundColor = Color(0xFF383838)
        ),
        contentPadding = PaddingValues(16.dp),
        onClick = { viewModel.addNewList() }
    ) {
        Icon(imageVector = Filled.Add, contentDescription = "Add")
        Spacer(modifier = Modifier.width(8.dp))
        Text(modifier = Modifier.weight(1f), fontSize = 16.sp, text = "Add List")
    }
}

/**
 * Returns the color for background of the drop surface,based on
 * whether a drop surface is in bounds, when a card is hovered on it.
 */
fun getDropSurfaceBgColor(
    isInBound: Boolean,
    isDragging: Boolean
): Color {
    return if (isInBound && isDragging) {
        Color(0xFF383838)
    } else {
        Color.Transparent
    }
}
