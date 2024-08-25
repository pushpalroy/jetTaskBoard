package com.jetapps.jettaskboard.board

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
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
    navigateToCreateCard: (boardId: Long, listId: Long, cardId: Long) -> Unit = { a, b, c -> },
    viewModel: TaskBoardViewModel,
    isExpandedScreen: Boolean,
    boardId: Long,
) {
    val boardState = remember { DragAndDropState(isExpandedScreen) }
    val boardList by viewModel.lists.collectAsState()
    var showListDialogState by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = boardState.movingCardData) {
        if (boardState.hasCardMoved()) {
            viewModel.moveCardToDifferentList(
                cardId = boardState.movingCardData.first,
                oldListId = boardState.cardDraggedInitialListId,
                newListId = boardState.movingCardData.second
            )
        }
    }

    if (showListDialogState) {
        CreateListDialog(
            onDismissRequest = { showListDialogState = false },
            onConfirmRequest = { title ->
                showListDialogState = false
                viewModel.addNewList(title)
            },
        )
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
                        }, boardId = boardId
                    )
                }
            }
            item {
                AddNewListButton(
                    isExpandedScreen = isExpandedScreen,
                    openListDialog = {
                        showListDialogState = true
                    }
                )
            }
        }
    }
}

@Composable
fun Lists(
    boardState: DragAndDropState,
    listModel: ListModel,
    onTaskCardClick: (boardId: Long, listId: Long, cardId: Long) -> Unit,
    onAddCardClick: () -> Unit,
    isExpandedScreen: Boolean,
    boardId: Long
) {
    DropSurface(
        modifier = Modifier
            .padding(start = 16.dp, end = 0.dp, top = 16.dp, bottom = 8.dp)
            .background(
                color = Color(0xFF222222),
                shape = RoundedCornerShape(2)
            ),
        listId = listModel.listId?.toInt() ?: 0
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
                name = listModel.title ?: ""
            )
            ListBody(
                modifier = Modifier,
                listModel = listModel,
                onTaskCardClick = onTaskCardClick,
                onAddCardClick = onAddCardClick,
                isExpandedScreen = isExpandedScreen,
                boardId = boardId
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListBody(
    modifier: Modifier,
    listModel: ListModel,
    onTaskCardClick: (boardId: Long, listId: Long, cardId: Long) -> Unit,
    onAddCardClick: () -> Unit,
    isExpandedScreen: Boolean,
    boardId: Long,
) {
    LazyColumn(
        modifier = Modifier
    ) {
        items(listModel.cards ?: emptyList()) { card ->
            DragSurface(
                modifier = modifier
                    .fillMaxWidth()
                    .animateItemPlacement(),
                cardId = card.id.toInt(),
                cardListId = card.listId.toInt() ?: 0
            ) {
                TaskCard(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onTaskCardClick(
                            boardId, listModel.listId ?: 0, card.id
                        )
                    },
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
    isExpandedScreen: Boolean,
    openListDialog: () -> Unit,
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
        onClick = openListDialog
    ) {
        Icon(imageVector = Filled.Add, contentDescription = "Add")
        Spacer(modifier = Modifier.width(8.dp))
        Text(modifier = Modifier.weight(1f), fontSize = 16.sp, text = "Add List")
    }
}

@Composable
fun CreateListDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirmRequest: (String) -> Unit,
) {
    var listTitle by remember {
        mutableStateOf<String>("")
    }
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Please Provide a name to your new list",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colors.onBackground
                )

                TextField(
                    value = listTitle,
                    onValueChange = {
                        listTitle = it
                    },
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(
                            "Dismiss",
                            modifier = Modifier.padding(16.dp),
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                    TextButton(
                        onClick = { onConfirmRequest(listTitle) },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(
                            "Confirm",
                            modifier = Modifier.padding(16.dp),
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }
            }
        }
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
