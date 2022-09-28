package com.jetapps.jettaskboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
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
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
      scaffoldState = scaffoldState,
      modifier = Modifier,
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
          modifier = Modifier,
          viewModel = viewModel
        )
      },
      floatingActionButtonPosition = FabPosition.End,
      floatingActionButton = {
        FloatingActionButton(onClick = { navigateToCreateBoard("") }) {
          Image(
            imageVector = Icons.Default.Add,
            contentDescription = "FAB Icon"
          )
        }
      }
    ) { scaffoldPadding ->
      LazyColumn(
        Modifier.padding(scaffoldPadding)
      ) {
        item {
          Header(
            modifier = Modifier, title = "Starred Boards", onMenuItemClicked = {}
          )
        }
        item {
          LazyVerticalGrid(
            modifier = Modifier
                .padding(top = 4.dp, bottom = 8.dp)
                .height(240.dp),
            columns = GridCells.Fixed(2),
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
        }
        item {
          Header(
            modifier = Modifier, title = "Trello workspace", onMenuItemClicked = {}
          )
        }
        item {
          LazyColumn(
            modifier = Modifier
                .height(320.dp)
                .padding(top = 4.dp),
          ) {
            items(viewModel.boardList) {
              WorkshopCard(
                modifier = Modifier.clickable { navigateToTaskBoard("") },
                title = it.title,
                imageUrl = it.imageUrl
              )
            }
          }
        }
      }
    }
  }
}