package com.jetapps.jettaskboard

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.jetapps.jettaskboard.components.TaskCard
import com.jetapps.jettaskboard.draganddrop.DragTarget
import com.jetapps.jettaskboard.draganddrop.DragTargetInfo
import com.jetapps.jettaskboard.draganddrop.DropTarget
import com.jetapps.jettaskboard.draganddrop.LongPressDraggable
import com.jetapps.jettaskboard.model.ListModel

@Composable
fun Board(
  modifier: Modifier = Modifier,
  navigateToCreateCard: (String) -> Unit = {},
  viewModel: TaskBoardViewModel
) {
  val boardState = remember { DragTargetInfo() }
  LaunchedEffect(Unit) {
    viewModel.apply {
      getBoardList()
    }
  }
  LongPressDraggable(
    modifier = modifier.fillMaxSize(),
    state = boardState
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState())
    ) {
      for (list in viewModel.boardList) {
        BoardList(
          boardState = boardState,
          boardList = list,
          onTaskCardClick = navigateToCreateCard,
          onAddCardClick = {
            viewModel.insertNewCardInList(list.id)
          }
        )
      }
    }
  }
}

@Composable
fun BoardList(
  boardState: DragTargetInfo,
  boardList: ListModel,
  onTaskCardClick: (String) -> Unit,
  onAddCardClick: () -> Unit
) {
  DropTarget(
    modifier = Modifier
      .padding(4.dp)
      .background(
        color = Color(0xFF222222),
        shape = RoundedCornerShape(2)
      )
  ) { isInBound, dragOffset ->
    val bgColor = if (isInBound && boardState.isDragging) {
      Color(0xFF383838)
    } else {
      Color.Transparent
    }
    Column(
      modifier = Modifier
        .background(color = bgColor, shape = RoundedCornerShape(2))
        .width(220.dp)
        .padding(4.dp)
    ) {
      ListHeader(name = boardList.title)
      LazyColumn(
        modifier = Modifier
      ) {
        items(
          items = boardList.cards,
          key = { it.id }
        ) {
          DragTarget(
            modifier = Modifier.fillMaxWidth(),
            cardDraggedId = it.id
          ) {
            TaskCard(
              modifier = Modifier.fillMaxWidth(),
              onClick = { onTaskCardClick("1") },
              card = it
            )
          }
        }
      }
      ListFooter(
        onAddCardClick = onAddCardClick
      )
    }
  }
}

@Composable
fun ListHeader(
  name: String
) {
  Row(
    modifier = Modifier
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
  onAddCardClick: () -> Unit
) {
  Row(
    modifier = Modifier
      .padding(start = 8.dp, top = 16.dp, end = 8.dp, bottom = 8.dp)
      .fillMaxWidth()
  ) {
    TextButton(
      modifier = Modifier
        .height(24.dp),
      contentPadding = PaddingValues(4.dp),
      onClick = { onAddCardClick() }
    ) {
      Icon(imageVector = Filled.Add, contentDescription = "Add")
      Text(modifier = Modifier, fontSize = 10.sp, text = "Add Card")
    }
  }
}