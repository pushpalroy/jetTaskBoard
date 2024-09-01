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
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jetapps.jettaskboard.CardDetailsRoute
import com.jetapps.jettaskboard.CreateCardRoute
import com.jetapps.jettaskboard.JtbNavDestination

// When User want to create a new Card from Dashboard
object CreateCardDestination : JtbNavDestination {
    override val route = "create_card_route"
    override val destination = "create_card_destination"
}

// When User want to edit/view/create a new card from TaskBoard
object CardDetailsDestination : JtbNavDestination {
    override val route = "card_details_route"
    override val destination = "card_details_destination"
}

fun NavGraphBuilder.cardGraph(
    isExpandedScreen: Boolean,
    onBackClick: () -> Unit,
) {
    composable(route = CreateCardDestination.route) {
        CreateCardRoute(isExpandedScreen = isExpandedScreen, onCancelClick = onBackClick)
    }
    composable(
        route = CardDetailsDestination.route + "/{boardId}/{listId}/{cardId}",
        arguments = listOf(
            navArgument("boardId") {
                type = NavType.LongType
                defaultValue = 0
            },
            navArgument("listId") {
                type = NavType.LongType
                defaultValue = 0
            },
            navArgument("cardId") {
                type = NavType.LongType
                defaultValue = 0
            },
        )
    ) { backStackEntry ->
        val passedBoardId = backStackEntry.arguments?.getLong("boardId")
        val passedListId = backStackEntry.arguments?.getLong("listId")
        val passedCardId = backStackEntry.arguments?.getLong("cardId")
        println("Passing Arguments : Board id -> $passedBoardId, ListId -> $passedListId and Card id -> $passedCardId")
        CardDetailsRoute(
            isExpandedScreen,
            onCancelClick = onBackClick,
        )
    }
}
