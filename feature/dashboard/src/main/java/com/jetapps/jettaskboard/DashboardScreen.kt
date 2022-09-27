package com.jetapps.jettaskboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.ui.Scaffold
import com.jetapps.jettaskboard.component.BoardCardComponent
import com.jetapps.jettaskboard.component.DashboardAppBar
import com.jetapps.jettaskboard.component.Header
import com.jetapps.jettaskboard.component.WorkshopCard
import com.jetapps.jettaskboard.drawer.JtbDrawer
import kotlinx.coroutines.launch

@Composable
fun DashboardRoute(
  modifier: Modifier = Modifier,
  viewModel: DashboardViewModel = hiltViewModel(),
  navigateToTaskBoard: (String) -> Unit = {},
  navigateToCreateCard: (String) -> Unit = {},
  navigateToCreateBoard: (String) -> Unit = {},
  isExpandedScreen: Boolean
) {
  Surface(
    modifier = modifier.fillMaxSize(),
    color = MaterialTheme.colors.background
  ) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scrollState: ScrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    Scaffold(
      scaffoldState = scaffoldState,
      modifier = modifier,
      topBar = {
        DashboardAppBar(
          onMenuIconClick = {
            scope.launch {
              scaffoldState.drawerState.open()
            }
          },
          onSearchIconClicked = { /*TODO*/ },
          onNotificationIconClicked = {}
        )
      },
      drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
      drawerContent = {
        JtbDrawer(
          modifier = modifier,
          viewModel = viewModel
        )
      },
      floatingActionButtonPosition = FabPosition.End,
      floatingActionButton = {
        FloatingActionButton(onClick = { /*TODO*/ }) {
          Image(
            imageVector = Icons.Default.Add,
            contentDescription = "FAB Icon"
          )
        }
      }
    ) { scaffoldPadding ->
      Column(
        modifier
          .verticalScroll(scrollState)
          .padding(scaffoldPadding)
      ) {
        Divider()

        Header(modifier = modifier, title = "Starred Boards") {}

        Divider()

        LazyVerticalGrid(
          modifier = modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .height(320.dp),
          columns = GridCells.Adaptive(minSize = 160.dp),
          contentPadding = PaddingValues(4.dp),
        ) {
          items(viewModel.boardList) { boardItem ->
            BoardCardComponent(
              modifier = Modifier.clickable { navigateToTaskBoard("") },
              title = boardItem.title,
              backgroundImageUrl = boardItem.imageUrl
            )
          }
        }

        Divider()

        Header(modifier = modifier, title = "Project One", showIcon = true) {}

        Divider()

        WorkshopCard(
          modifier = modifier,
          title = "Project One",
          isWorkshopStarred = true,
          imageUrl = viewModel.getRandomImageUrl().random()
        )

        Divider()

        Header(modifier = modifier, title = "Trello Workshop", showIcon = true) {}

        Divider()

        LazyColumn(
          modifier = modifier.size(320.dp),
        ) {
          items(viewModel.boardList) {
            WorkshopCard(modifier, title = it.title, imageUrl = it.imageUrl)
          }
        }

        Divider()
      }
    }
  }
}