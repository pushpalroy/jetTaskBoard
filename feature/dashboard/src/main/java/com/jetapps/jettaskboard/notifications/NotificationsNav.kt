package com.jetapps.jettaskboard.notifications

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jetapps.jettaskboard.JtbNavDestination
import com.jetapps.jettaskboard.feature_search.presentation.SearchRoute

object NotificationsDestination : JtbNavDestination {
    override val route = "notifications_route"
    override val destination = "notifications_destination"
}

fun NavGraphBuilder.notificationsGraph(isExpandedScreen: Boolean, onBackClick: () -> Unit) {
    composable(route = NotificationsDestination.route) {
        NotificationsRoute(
            isExpandedScreen = isExpandedScreen,
            onNavigateUp = onBackClick,
            navigateToCardScreen = {},
            navigateToBoardScreen = {}
        )
    }
    composable(route = NotificationsDestination.route) {
        NotificationsRoute(
            isExpandedScreen = isExpandedScreen,
            onNavigateUp = onBackClick,
            navigateToCardScreen = {},
            navigateToBoardScreen = {}
        )
    }
}