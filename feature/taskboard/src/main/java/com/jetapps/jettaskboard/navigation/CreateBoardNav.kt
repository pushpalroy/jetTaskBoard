package com.jetapps.jettaskboard.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jetapps.jettaskboard.JtbNavDestination
import com.jetapps.jettaskboard.createboard.CreateBoardRoute

object CreateBoardDestination : JtbNavDestination {
    override val route: String = "create_board_route"
    override val destination: String = "create_board_destination"
}

fun NavGraphBuilder.createBoardGraph(
    onBackClick: () -> Unit,
    navigateToBoardRoute: () -> Unit,
) {
    composable(route = CreateBoardDestination.route) {
        CreateBoardRoute(
            modifier = Modifier,
            onCancelClick = onBackClick,
            navigateToBoardScreen = navigateToBoardRoute,
        )
    }
}
