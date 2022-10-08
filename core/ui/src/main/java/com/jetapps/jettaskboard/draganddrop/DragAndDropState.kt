package com.jetapps.jettaskboard.draganddrop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset

class DragAndDropState constructor(
  var isExpandedScreen: Boolean = false
) {
  /**
   * The composable or piece of UI or simply an item that can be dragged around
   */
  var draggableItem by mutableStateOf<(@Composable () -> Unit)?>(null)

  /**
   * Whether the drag gesture has been detected on the item
   */
  var isDragging by mutableStateOf(false)

  /**
   * Current position of the item to be dragged
   */
  var itemPosition by mutableStateOf(Offset.Zero)

  /**
   * Current position of the drag pointer after drag is started
   */
  var dragOffset by mutableStateOf(Offset.Zero)

  var cardDraggedId by mutableStateOf(-1)
  var cardDraggedListId by mutableStateOf(-1)
  var listIdHasCardInBounds by mutableStateOf(-1)
  var movingCardData by mutableStateOf(INITIAL_CARD_LIST_PAIR)

  companion object {
    val INITIAL_CARD_LIST_PAIR = Pair(-1, -1)
  }
}

internal val LocalDragAndDropState = compositionLocalOf { DragAndDropState() }