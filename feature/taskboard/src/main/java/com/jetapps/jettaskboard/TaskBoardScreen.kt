package com.jetapps.jettaskboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
          Button(
            modifier = Modifier.padding(16.dp),
            onClick = onBackClick
          ) {
            Text(text = "Go to back to dash board")
          }
        }
      }
    )
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