package com.jetapps.jettaskboard.navigation

import androidx.navigation.NavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jetapps.jettaskboard.JtbNavDestination
import com.jetapps.jettaskboard.board.TaskBoardRoute

object TaskBoardDestination : JtbNavDestination {
    override val route = "task_board_route"
    override val destination = "task_board_destination"
}

fun NavGraphBuilder.taskBoardGraph(
    isExpandedScreen: Boolean,
    navigateToCreateCard: (boardId: Long, listId: Long, cardId: Long) -> Unit,
    navigateToChangeBackgroundScreen: (String) -> Unit = {},
    onBackClick: () -> Unit
) {
    composable(
        route = TaskBoardDestination.route + "/{boardId}",
        arguments = listOf(
            navArgument("boardId") {
                type = NavType.LongType
                defaultValue = 0
            },
        )
    ) { backStackEntry ->
        TaskBoardRoute(
            isExpandedScreen = isExpandedScreen,
            navigateToCreateCard = navigateToCreateCard,
            onBackClick = onBackClick,
            navigateToChangeBackgroundScreen = navigateToChangeBackgroundScreen,
            boardId = backStackEntry.arguments?.getLong("boardId")
        )
    }
}
