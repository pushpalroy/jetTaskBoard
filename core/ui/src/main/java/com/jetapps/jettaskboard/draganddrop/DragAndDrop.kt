package com.jetapps.jettaskboard.draganddrop

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
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
fun LongPressDraggable(
  modifier: Modifier = Modifier,
  state: DragInfoState = remember { DragInfoState() },
  content: @Composable BoxScope.() -> Unit
) {
  CompositionLocalProvider(
    LocalDragInfoState provides state
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
            .width(200.dp)
            .rotate(5f)
            .graphicsLayer {
              val offset = (state.dragPosition + state.dragOffset)
              scaleX = 1f
              scaleY = 1f
              alpha = if (targetSize == IntSize.Zero) 0f else 1f
              translationX = offset.x.minus(targetSize.width / 2)
              // 160f is the height adjustment for top app bar
              translationY = offset.y.minus((targetSize.height / 2 + 160f))
              spotShadowColor = Color(0xFF111111)
              ambientShadowColor = Color(0xFF111111)
            }
            .onGloballyPositioned {
              targetSize = it.size
            }
        ) {
          state.draggableComposable?.invoke()
        }
      }
    }
  }
}

@Composable
fun DragTarget(
  modifier: Modifier,
  currentListId: Int = 0,
  cardDraggedId: Int = 0,
  content: @Composable (() -> Unit)
) {
  var currentPosition by remember { mutableStateOf(Offset.Zero) }
  val dragInfoState = LocalDragInfoState.current
  var targetHeight by remember {
    mutableStateOf(0)
  }
  Box(
    modifier = modifier
      .onGloballyPositioned {
        currentPosition = it.localToWindow(Offset.Zero)
        targetHeight = it.size.height
      }
      .pointerInput(Unit) {
        detectDragGesturesAfterLongPress(
          onDragStart = {
            dragInfoState.isDragging = true
            dragInfoState.dragPosition = currentPosition + it
            dragInfoState.draggableComposable = content
            dragInfoState.cardDraggedId = cardDraggedId
            dragInfoState.cardDraggedListId = currentListId
          },
          onDrag = { change, dragAmount ->
            change.consume()
            dragInfoState.dragOffset += Offset(dragAmount.x, dragAmount.y)
          },
          onDragEnd = {
            dragInfoState.isDragging = false
            dragInfoState.dragOffset = Offset.Zero
          },
          onDragCancel = {
            dragInfoState.dragOffset = Offset.Zero
            dragInfoState.isDragging = false
          }
        )
      }
  ) {
    if (dragInfoState.cardDraggedId != cardDraggedId) {
      content()
    } else {
      AnimatedVisibility(visible = dragInfoState.isDragging) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(LocalDensity.current.run { targetHeight.toDp() })
        )
      }
    }
  }
}

@Composable
fun DroppingArea(
  modifier: Modifier,
  listId: Int,
  content: @Composable (BoxScope.(isInBound: Boolean, dragOffset: Offset) -> Unit)
) {
  val dragInfoState = LocalDragInfoState.current
  val dragPosition = dragInfoState.dragPosition
  val dragOffset = dragInfoState.dragOffset
  var isCurrentDropTarget by remember {
    mutableStateOf(false)
  }
  Box(
    modifier = modifier.onGloballyPositioned {
      it.boundsInWindow().let { rect ->
        isCurrentDropTarget = rect.contains(dragPosition + dragOffset)
        if (isCurrentDropTarget && listId != dragInfoState.cardDraggedListId) {
          dragInfoState.cardDraggedListId = listId
        }
      }
    }
  ) {
    content(isCurrentDropTarget, dragOffset)
  }
}

class DragInfoState {
  var isDragging by mutableStateOf(false)
  var dragPosition by mutableStateOf(Offset.Zero)
  var dragOffset by mutableStateOf(Offset.Zero)
  var draggableComposable by mutableStateOf<(@Composable () -> Unit)?>(null)
  var cardDraggedId by mutableStateOf(-1)
  var cardDraggedListId by mutableStateOf(-1)
}

internal val LocalDragInfoState = compositionLocalOf { DragInfoState() }