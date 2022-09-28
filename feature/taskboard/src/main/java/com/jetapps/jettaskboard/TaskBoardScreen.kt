package com.jetapps.jettaskboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetapps.jettaskboard.feature.taskboard.R
import com.jetapps.jettaskboard.zoomable.Zoomable
import com.jetapps.jettaskboard.zoomable.rememberZoomableState
import kotlinx.coroutines.CoroutineScope

@Composable
fun TaskBoardRoute(
  onBackClick: () -> Unit,
  modifier: Modifier = Modifier,
  isExpandedScreen: Boolean,
  navigateToCreateCard: (String) -> Unit = {},
  viewModel: TaskBoardViewModel = hiltViewModel()
) {
  val scaffoldState = rememberScaffoldState()
  Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
      TopAppBar(
        onBackClick = onBackClick,
        title = viewModel.boardInfo.value.second
      )
    },
    floatingActionButton = { FAB(onClick = {}) }
  ) {
    Surface(
      modifier = modifier
        .fillMaxSize(),
      color = MaterialTheme.colors.background
    ) {
      Box(
        modifier = Modifier
          .fillMaxSize()
      ) {
        Image(
          painter = painterResource(R.drawable.bg_board),
          contentDescription = "background",
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .matchParentSize()
        )
        Zoomable(
          coroutineScope = rememberCoroutineScope()
        ) {
          Board(
            modifier = Modifier.fillMaxSize(),
            navigateToCreateCard = navigateToCreateCard,
            viewModel = viewModel
          )
        }
      }
    }
  }
}

@Composable
fun Zoomable(
  coroutineScope: CoroutineScope,
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

@Composable
fun TopAppBar(
  onBackClick: () -> Unit,
  title: String
) {
  TopAppBar(
    navigationIcon = {
      IconButton(
        onClick = onBackClick
      ) {
        Icon(
          Icons.Default.ArrowBack,
          contentDescription = "Back"
        )
      }
    },
    title = { Text(text = title) }
  )
}

@Composable
fun FAB(
  onClick: () -> Unit
) {
  FloatingActionButton(onClick = onClick) {
    Icon(
      Icons.Default.Add,
      contentDescription = "Zoom"
    )
  }
}