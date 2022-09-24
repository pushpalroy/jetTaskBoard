package com.jetapps.jettaskboard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jetapps.jettaskboard.JtbNavDestination
import com.jetapps.jettaskboard.TaskBoardRoute

object TaskBoardDestination : JtbNavDestination {
  override val route = "task_board_route"
  override val destination = "task_board_destination"
}

fun NavGraphBuilder.taskBoardGraph(isExpandedScreen: Boolean ,onBackClick: () -> Unit) {
  composable(route = TaskBoardDestination.route) {
    TaskBoardRoute(isExpandedScreen = isExpandedScreen , onBackClick = onBackClick)
  }
}