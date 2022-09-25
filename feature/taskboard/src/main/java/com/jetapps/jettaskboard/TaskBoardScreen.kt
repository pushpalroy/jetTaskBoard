package com.jetapps.jettaskboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetapps.jettaskboard.reorderable.ReorderableItem
import com.jetapps.jettaskboard.reorderable.detectReorderAfterLongPress
import com.jetapps.jettaskboard.reorderable.rememberReorderableLazyListState
import com.jetapps.jettaskboard.reorderable.reorderable
import com.jetapps.jettaskboard.zoomable.Zoomable
import com.jetapps.jettaskboard.zoomable.rememberZoomableState
import kotlinx.coroutines.CoroutineScope

@Composable
fun TaskBoardRoute(
  onBackClick: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: TaskBoardViewModel = hiltViewModel()
) {
  Surface(
    modifier = modifier.fillMaxSize(),
    color = MaterialTheme.colors.background
  ) {
    val coroutineScope = rememberCoroutineScope()
    ZoomableBoard(
      coroutineScope = coroutineScope,
      content = {
        Box(
          modifier = modifier.fillMaxSize()
        ) {
          VerticalReorderList()
        }
      }
    )
  }
}

@Composable
fun VerticalReorderList() {
  val data = remember { mutableStateOf(List(8) { "Item $it" }) }
  val state = rememberReorderableLazyListState(
    onMove = { from, to ->
      data.value = data.value.toMutableList().apply {
        add(to.index, removeAt(from.index))
      }
    }
  )
  Row(
    modifier = Modifier
      .fillMaxWidth()
  ) {
    LazyColumn(
      state = state.listState,
      modifier = Modifier
        .padding(24.dp)
        .wrapContentWidth()
        .reorderable(state)
        .detectReorderAfterLongPress(state)
    ) {
      items(
        items = data.value,
        key = { it }
      ) { item ->
        ReorderableItem(
          modifier = Modifier
            .width(150.dp),
          reorderableState = state,
          orientationLocked = false,
          key = item
        ) {
          Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
          ) {
            Text(text = "Card item $item")
          }
        }
      }
    }
  }
}

@Composable
fun ZoomableBoard(
  coroutineScope: CoroutineScope,
  onSwipeLeft: () -> Unit = {},
  onSwipeRight: () -> Unit = {},
  content: @Composable (BoxScope.() -> Unit)
) {
  val zoomableState = rememberZoomableState()
  Zoomable(
    coroutineScope = coroutineScope,
    zoomableState = zoomableState,
    onSwipeLeft = {},
    onSwipeRight = {}
  ) {
    content()
  }
}