package com.jetapps.jettaskboard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jetapps.jettaskboard.JtbNavDestination
import com.jetapps.jettaskboard.change_bg.ChangeBoardBackgroundRoute

object ChangeBoardBackgroundDestination : JtbNavDestination {
    override val route: String = "change_board_background_route"
    override val destination: String = "change_board_background_destination"
}

fun NavGraphBuilder.changeBoardBackgroundGraph(
    isExpandedScreen: Boolean,
    onBackClick: () -> Unit
) {
    composable(route = ChangeBoardBackgroundDestination.route) {
        ChangeBoardBackgroundRoute(
            isExpandedScreen = isExpandedScreen,
            onBackClick = onBackClick
        )
    }
}