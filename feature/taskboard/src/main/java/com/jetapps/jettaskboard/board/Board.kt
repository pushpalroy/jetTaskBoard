package com.jetapps.jettaskboard.board

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollBy
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetapps.jettaskboard.TaskBoardViewModel
import com.jetapps.jettaskboard.components.TaskCard
import com.jetapps.jettaskboard.draganddrop.DragInfoState
import com.jetapps.jettaskboard.draganddrop.DragTarget
import com.jetapps.jettaskboard.draganddrop.DroppingArea
import com.jetapps.jettaskboard.draganddrop.LongPressDraggable
import com.jetapps.jettaskboard.model.ListModel
import com.jetapps.jettaskboard.theme.SecondaryColor
import kotlinx.coroutines.launch

@Composable
fun Board(
  modifier: Modifier = Modifier,
  navigateToCreateCard: (String) -> Unit = {},
  viewModel: TaskBoardViewModel
) {
  val boardState = remember { DragInfoState() }
  LaunchedEffect(Unit) {
    viewModel.apply {
      getBoardData()
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
      for (list in viewModel.lists) {
        Lists(
          boardState = boardState,
          listModel = list,
          onTaskCardClick = navigateToCreateCard,
          onAddCardClick = {
            viewModel.addNewCardInList(list.id)
          },
          viewModel = viewModel
        )
      }
    }
  }
}

@Composable
fun Lists(
  boardState: DragInfoState,
  listModel: ListModel,
  viewModel: TaskBoardViewModel,
  onTaskCardClick: (String) -> Unit,
  onAddCardClick: () -> Unit
) {
  val scrollState = rememberScrollState()
  val scope = rememberCoroutineScope()
  val cards = remember { viewModel.cards }

  val view = LocalView.current
  val screenHeight = view.rootView.height

  // Always scroll to bottom when size changes
  LaunchedEffect(
    key1 = cards.size,
    block = {
      scope.launch {
        scrollState.scrollBy(screenHeight.toFloat())
      }
    }
  )

  DroppingArea(
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
        .width(240.dp)
        .padding(4.dp)
    ) {
      ListHeader(
        name = listModel.title
      )
      Column(
        modifier = Modifier.verticalScroll(scrollState)
      ) {
        for (cardModel in cards) {
          if (cardModel.listId == listModel.id) {
            DragTarget(
              modifier = Modifier.fillMaxWidth(),
              currentListId = cardModel.listId ?: 0,
              cardDraggedId = cardModel.id
            ) {
              TaskCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onTaskCardClick("1") },
                card = cardModel
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