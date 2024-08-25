/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jetapps.jettaskboard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jetapps.jettaskboard.DashboardRoute
import com.jetapps.jettaskboard.JtbNavDestination

object DashboardDestination : JtbNavDestination {
    override val route = "dashboard_route"
    override val destination = "dashboard_destination"
}

fun NavGraphBuilder.dashboardGraph(
    navigateToTaskBoard: (String) -> Unit,
    navigateToCreateCard: () -> Unit,
    navigateToCreateBoard: () -> Unit,
    navigateToSearchScreen: (String) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
    isExpandedScreen: Boolean
) {
    composable(route = DashboardDestination.route) {
        DashboardRoute(
            navigateToTaskBoard = navigateToTaskBoard,
            navigateToCreateCard = navigateToCreateCard,
            navigateToCreateBoard = navigateToCreateBoard,
            navigateToSearchScreen = navigateToSearchScreen,
            isExpandedScreen = isExpandedScreen
        )
    }

    // If a nested graph is needed here
    // navigation(
    //   route = DashboardDestination.route,
    //   startDestination = DashboardDestination.destination
    // ) {
    //   composable(route = DashboardDestination.destination) {
    //     DashboardRoute(
    //       navigateToTaskBoard = navigateToTaskBoard,
    //       navigateToCreateCard = navigateToCreateCard,
    //       navigateToCreateBoard = navigateToCreateBoard,
    //       navigateToSearchScreen = navigateToSearchScreen,
    //       isExpandedScreen = isExpandedScreen
    //     )
    //   }
    //   nestedGraphs()
    // }
}
