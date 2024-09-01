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

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jetapps.jettaskboard.JtbNavDestination
import com.jetapps.search.SearchDestination
import com.jetapps.search.searchGraph

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun JtbNavHost(
    navController: NavHostController,
    onNavigateToDestination: (JtbNavDestination, String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = DashboardDestination.route,
    isExpandedScreen: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        dashboardGraph(
            isExpandedScreen = isExpandedScreen,
            navigateToTaskBoard = { boardId ->
                onNavigateToDestination(
                    TaskBoardDestination,
                    TaskBoardDestination.route + "/$boardId"
                )
            },
            navigateToCreateCard = {
                onNavigateToDestination(
                    CreateCardDestination,
                    CreateCardDestination.route
                )
            },
            navigateToCreateBoard = {
                onNavigateToDestination(
                    CreateBoardDestination,
                    CreateBoardDestination.route
                )
            },
            navigateToSearchScreen = {
                onNavigateToDestination(
                    SearchDestination,
                    SearchDestination.route
                )
            },
            nestedGraphs = {}
        )

        taskBoardGraph(
            isExpandedScreen = isExpandedScreen,
            onBackClick = onBackClick,
            navigateToCreateCard = { boardId, listId, cardId ->
                onNavigateToDestination(
                    CardDetailsDestination,
                    CardDetailsDestination.route + "/$boardId" + "/$listId" + "/$cardId"
                )
            },
            navigateToChangeBackgroundScreen = {
                onNavigateToDestination(
                    ChangeBoardBackgroundDestination,
                    ChangeBoardBackgroundDestination.route
                )
            }
        )

        cardGraph(
            isExpandedScreen = isExpandedScreen,
            onBackClick = onBackClick
        )

        createBoardGraph(
            onBackClick = onBackClick,
            navigateToBoardRoute = { passedId ->
                // Clear the Create Board Screen then only
                // Navigate to the TaskBoard Screen
                navController.popBackStack()
                onNavigateToDestination(
                    TaskBoardDestination,
                    TaskBoardDestination.route + "/$passedId",
                )
            }
        )

        searchGraph(
            isExpandedScreen = isExpandedScreen,
            onBackClick = onBackClick
        )

        changeBoardBackgroundGraph(
            isExpandedScreen = isExpandedScreen,
            onBackClick = onBackClick
        )
    }
}
