package com.jetapps.jettaskboard.board

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetapps.jettaskboard.TaskBoardViewModel
import com.jetapps.jettaskboard.components.TaskCard
import com.jetapps.jettaskboard.draganddrop.DragAndDropState
import com.jetapps.jettaskboard.draganddrop.DragAndDropState.Companion.INITIAL_CARD_LIST_PAIR
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
  LaunchedEffect(Unit) {
    viewModel.apply {
      getBoardData()
    }
  }
  LaunchedEffect(key1 = boardState.movingCardData) {
    viewModel.apply {
      if (boardState.movingCardData != INITIAL_CARD_LIST_PAIR) {
        moveCardToDifferentList(
          cardId = boardState.movingCardData.first,
          oldListId = boardState.cardDraggedInitialListId,
          newListId = boardState.movingCardData.second
        )
      }
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
      items(viewModel.lists) { list ->
        Lists(
          boardState = boardState,
          listModel = list,
          onTaskCardClick = navigateToCreateCard,
          onAddCardClick = {
            viewModel.addNewCardInList(list.id)
          },
          isExpandedScreen = isExpandedScreen
        )
      }
    }
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Lists(
  boardState: DragAndDropState,
  listModel: ListModel,
  onTaskCardClick: (String) -> Unit,
  onAddCardClick: () -> Unit,
  isExpandedScreen: Boolean,
) {
  val cards = remember { listModel.cards }

  // val scope = rememberCoroutineScope()
  // val screenHeight = LocalView.current.rootView.height
  // Always scroll to bottom when size changes
  // LaunchedEffect(
  //   key1 = cards.size,
  //   block = {
  //     scope.launch {
  //       scrollState.scrollBy(screenHeight.toFloat())
  //     }
  //   }
  // )

  DropSurface(
    modifier = Modifier
      .padding(start = 16.dp, end = 0.dp, top = 16.dp, bottom = 8.dp)
      .background(
        color = Color(0xFF222222),
        shape = RoundedCornerShape(2)
      ),
    listId = listModel.id
  ) { isInBound, _ ->
    Column(
      modifier = Modifier
        .background(
          color = getBgColor(isInBound, boardState.isDragging),
          shape = RoundedCornerShape(2)
        )
        .width(if (isExpandedScreen) 300.dp else 240.dp)
        .padding(if (isExpandedScreen) 8.dp else 4.dp)
    ) {
      ListHeader(
        name = listModel.title
      )
      LazyColumn(
        modifier = Modifier
      ) {
        items(cards) { card ->
          DragSurface(
            modifier = Modifier
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

fun getBgColor(
  isInBound: Boolean,
  isDragging: Boolean
): Color {
  return if (isInBound && isDragging) {
    Color(0xFF383838)
  } else {
    Color.Transparent
  }
}