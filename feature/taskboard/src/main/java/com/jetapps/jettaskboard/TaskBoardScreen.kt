package com.jetapps.jettaskboard

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetapps.jettaskboard.components.TaskCard
import com.jetapps.jettaskboard.draganddrop.DragTarget
import com.jetapps.jettaskboard.draganddrop.DragTargetInfo
import com.jetapps.jettaskboard.draganddrop.DropTarget
import com.jetapps.jettaskboard.draganddrop.LongPressDraggable
import com.jetapps.jettaskboard.zoomable.Zoomable
import com.jetapps.jettaskboard.zoomable.rememberZoomableState
import kotlinx.coroutines.CoroutineScope

@Composable
fun TaskBoardRoute(
  onBackClick: () -> Unit,
  modifier: Modifier = Modifier,
  isExpandedScreen: Boolean,
  viewModel: TaskBoardViewModel = hiltViewModel()
) {
  val scaffoldState = rememberScaffoldState()
  Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
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
        title = {
          Text(text = viewModel.getFakeBoardData().title)
        }
      )
    },
    floatingActionButton = {
      FloatingActionButton(onClick = {}) {
        Icon(
          Icons.Default.Add,
          contentDescription = "Zoom"
        )
      }
    }
  ) {
    Surface(
      modifier = modifier
        .padding(top = 8.dp)
        .fillMaxSize(),
      color = MaterialTheme.colors.background
    ) {
      val coroutineScope = rememberCoroutineScope()
      Zoomable(
        coroutineScope = coroutineScope,
        content = {
          Box(
            modifier = modifier.fillMaxSize()
          ) {
            Board(
              viewModel = viewModel
            )
          }
        }
      )
    }
  }
}

@Composable
fun Board(
  modifier: Modifier = Modifier,
  viewModel: TaskBoardViewModel
) {
  val boardState = remember { DragTargetInfo() }

  Box(
    modifier = modifier.fillMaxSize()
  ) {
    LongPressDraggable(
      modifier = Modifier.fillMaxSize(),
      state = boardState
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(
            state = rememberScrollState()
          ),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        for (list in viewModel.getFakeBoardData().lists) {
          DropTarget(
            modifier = Modifier
              .padding(8.dp)
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
              Row(
                modifier = Modifier
                  .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 16.dp)
                  .fillMaxWidth()
              ) {
                Text(modifier = Modifier.weight(1f), text = list.title)
                IconButton(modifier = Modifier.size(16.dp), onClick = {}) {
                  Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Menu")
                }
              }
              LazyColumn(
                modifier = Modifier
              ) {
                items(
                  items = list.cards,
                  key = { it.id }
                ) {
                  DragTarget(
                    modifier = Modifier.fillMaxWidth(),
                    cardDraggedId = it.id
                  ) {
                    TaskCard(
                      modifier = Modifier.fillMaxWidth(),
                      onClick = {},
                      card = it
                    )
                  }
                }
              }
              Row(
                modifier = Modifier
                  .padding(start = 8.dp, top = 16.dp, end = 8.dp, bottom = 8.dp)
                  .fillMaxWidth()
              ) {
                TextButton(
                  modifier = Modifier
                    .height(24.dp),
                  contentPadding = PaddingValues(4.dp),
                  onClick = {}
                ) {
                  Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                  Text(modifier = Modifier, fontSize = 10.sp, text = "Add Card")
                }
              }
            }
          }
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