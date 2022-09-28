package com.jetapps.jettaskboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.ui.Scaffold
import com.jetapps.jettaskboard.component.DashboardAppBar
import com.jetapps.jettaskboard.drawer.JtbDrawer
import com.jetapps.jettaskboard.navigation.JtbDrawerShape
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
    val scaffoldState = rememberSizeAwareScaffoldState(isExpandedScreen)
    val scope = rememberCoroutineScope()

    Scaffold(
      scaffoldState = scaffoldState,
      modifier = Modifier,
      topBar = {
        DashboardAppBar(
          isExpandedScreen = isExpandedScreen,
          onMenuIconClick = {
            scope.launch {
              if (isExpandedScreen.not()) {
                scaffoldState.drawerState.open()
              }
            }
          },
          onSearchIconClicked = {},
          onNotificationIconClicked = {}
        )
      },
      // Gestures are enabled only on smaller and medium screens
      drawerGesturesEnabled = isExpandedScreen.not(),
      drawerShape = JtbDrawerShape(),
      drawerContent = {
        // Modal drawer is available only on smaller and medium screens
        if (isExpandedScreen.not()) {
          JtbDrawer(
              modifier = Modifier.fillMaxSize(),
              viewModel = viewModel
          )
        }
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
      Row(Modifier.fillMaxSize()) {
        // Show permanent drawer only for large screens
        if (isExpandedScreen) {
          Column {
            Spacer(modifier = Modifier.height(56.dp))
            JtbDrawer(
                modifier = Modifier
                    .width(320.dp),
                viewModel = viewModel
            )
          }
        }
        if (isExpandedScreen.not()) {
          DashboardSinglePaneContent(
              paddingValues = scaffoldPadding,
              viewModel = viewModel,
              navigateToTaskBoard = navigateToTaskBoard
          )
        } else {
          DashboardTwoPaneContent(
              paddingValues = scaffoldPadding,
              viewModel = viewModel,
              navigateToTaskBoard = navigateToTaskBoard
          )
        }
      }
    }
  }
}



@Composable
private fun rememberSizeAwareScaffoldState(
  isExpandedScreen: Boolean
): ScaffoldState {
  val commonSnackBarHostState = remember { SnackbarHostState() }
  val compactScaffoldState = rememberScaffoldState(
      drawerState = rememberDrawerState(DrawerValue.Closed),
      snackbarHostState = commonSnackBarHostState
  )
  val expandedScaffoldState = rememberScaffoldState(
      drawerState = DrawerState(DrawerValue.Closed),
      snackbarHostState = commonSnackBarHostState
  )
  return if (isExpandedScreen) {
    expandedScaffoldState
  } else {
    compactScaffoldState
  }
}