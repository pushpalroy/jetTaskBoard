package com.jetapps.jettaskboard.draganddrop

import android.util.Log
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun DragAndDropSurface(
  modifier: Modifier = Modifier,
  state: DragAndDropState = remember { DragAndDropState() },
  content: @Composable BoxScope.() -> Unit
) {
  CompositionLocalProvider(
    LocalDragAndDropState provides state
  ) {
    Box(modifier = modifier.wrapContentWidth())
    {
      content()
      if (state.isDragging) {
        var targetSize by remember {
          mutableStateOf(IntSize.Zero)
        }
        Box(
          modifier = Modifier
            .width(if (state.isExpandedScreen) 300.dp else 240.dp)
            .rotate(5f)
            .graphicsLayer {
              val offset = (state.itemPosition + state.dragOffset)
              scaleX = 0.9f
              scaleY = 0.9f
              alpha = if (targetSize == IntSize.Zero) 0f else 1f
              translationX = offset.x.minus(targetSize.width / 2)
              // 160f is the height adjustment for top app bar, need to find a way to calculate this height
              translationY = offset.y.minus((targetSize.height / 2 + 160f))
              spotShadowColor = Color(0xFF111111)
              ambientShadowColor = Color(0xFF111111)
            }
            .onGloballyPositioned {
              targetSize = it.size
            }
        ) {
          state.draggableItem?.invoke()
        }
      }
    }
  }
}

@Composable
fun DragSurface(
  modifier: Modifier,
  cardId: Int = 0,
  cardListId: Int = 0,
  content: @Composable () -> Unit
) {
  val dragNDropState = LocalDragAndDropState.current
  var currentPosition by remember { mutableStateOf(Offset.Zero) }
  var targetHeight by remember { mutableStateOf(0) }

  Box(
    modifier = modifier
      .onGloballyPositioned {
        currentPosition = it.localToWindow(Offset.Zero)
        targetHeight = it.size.height
      }
      .pointerInput(key1 = cardId) {
        detectDragGesturesAfterLongPress(
          onDragStart = {
            with(dragNDropState) {
              isDragging = true
              itemPosition = currentPosition + it
              draggableItem = content

              // Metadata
              cardDraggedId = cardId
              cardDraggedCurrentListId = cardListId
              cardDraggedInitialListId = cardListId
              listIdWithCardInBounds = cardListId
            }
          },
          onDrag = { change, dragAmount ->
            change.consume()
            dragNDropState.dragOffset += Offset(dragAmount.x, dragAmount.y)
          },
          onDragEnd = {
            with(dragNDropState) {
              isDragging = false
              dragOffset = Offset.Zero

              // Metadata
              if (cardDraggedCurrentListId != listIdWithCardInBounds) {
                // Move card action detected
                movingCardData = Pair(cardDraggedId, listIdWithCardInBounds)
                cardDraggedCurrentListId = listIdWithCardInBounds
              }
            }
          },
          onDragCancel = {
            with(dragNDropState) {
              isDragging = false
              dragOffset = Offset.Zero

              // Metadata
              cardDraggedId = -1
              cardDraggedCurrentListId = -1
              cardDraggedInitialListId = -1
              listIdWithCardInBounds = -1
              movingCardData = DragAndDropState.INITIAL_CARD_LIST_PAIR
            }
          }
        )
      }
  ) {

    // Experimental animation to show empty space while dragging
    if (dragNDropState.isDragging && dragNDropState.cardDraggedId == cardId) {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(LocalDensity.current.run { targetHeight.toDp() })
      )
    } else {
      content()
    }
  }
}

@Composable
fun DropSurface(
  modifier: Modifier,
  listId: Int,
  content: @Composable BoxScope.(isInBound: Boolean, dragOffset: Offset) -> Unit
) {
  val dragNDropState = LocalDragAndDropState.current
  val dragPosition = dragNDropState.itemPosition
  val dragOffset = dragNDropState.dragOffset
  var isCurrentDropTarget by remember {
    mutableStateOf(false)
  }
  Box(
    modifier = modifier.onGloballyPositioned {
      it.boundsInWindow().let { rect ->
        isCurrentDropTarget = rect.contains(dragPosition + dragOffset)

        // Changing list id when card moved from one list to another
        if (isCurrentDropTarget && listId != dragNDropState.cardDraggedCurrentListId) {
          dragNDropState.listIdWithCardInBounds = listId
        }
      }
    }
  ) {
    content(isCurrentDropTarget, dragOffset)
  }
}