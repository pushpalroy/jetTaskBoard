package com.jetapps.jettaskboard.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jetapps.jettaskboard.JtbNavDestination
import com.jetapps.jettaskboard.TaskBoardRoute
import com.jetapps.jettaskboard.createboard.CreateBoardRoute

object TaskBoardDestination : JtbNavDestination {
  override val route = "task_board_route"
  override val destination = "task_board_destination"
}

fun NavGraphBuilder.taskBoardGraph(
  isExpandedScreen: Boolean,
  navigateToCreateCard: (String) -> Unit = {},
  onBackClick: () -> Unit
) {
  composable(route = TaskBoardDestination.route) {
    TaskBoardRoute(
      isExpandedScreen = isExpandedScreen,
      navigateToCreateCard = navigateToCreateCard,
      onBackClick = onBackClick
    )
  }
}


object CreateBoardDestination : JtbNavDestination {
  override val route: String = "create_board_route"
  override val destination: String = "create_board_destination"
}

fun NavGraphBuilder.createBoardGraph(onBackClick: () -> Unit){
  composable(route =  CreateBoardDestination.route){
    CreateBoardRoute(onCancelClick = onBackClick, Modifier)
  }
}