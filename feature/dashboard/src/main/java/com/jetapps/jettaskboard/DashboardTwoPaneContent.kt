package com.jetapps.jettaskboard

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane
import com.google.accompanist.adaptive.calculateDisplayFeatures
import com.jetapps.jettaskboard.component.BoardCardComponent
import com.jetapps.jettaskboard.component.Header
import com.jetapps.jettaskboard.component.WorkshopCard

@Composable
fun DashboardTwoPaneContent(
    paddingValues: PaddingValues,
    viewModel: DashboardViewModel,
    navigateToTaskBoard: (String) -> Unit = {},
) {
    val context = LocalContext.current
    TwoPane(
        first = {
            LeftPane(
                paddingValues = paddingValues,
                viewModel = viewModel,
                navigateToTaskBoard = navigateToTaskBoard
            )
        },
        second = {
            RightPane(
                paddingValues = paddingValues,
                viewModel = viewModel,
                navigateToTaskBoard = navigateToTaskBoard
            )
        },
        strategy = { density, layoutDirection, layoutCoordinates ->
            HorizontalTwoPaneStrategy(splitFraction = .5f)
                .calculateSplitResult(density, layoutDirection, layoutCoordinates)
        },
        displayFeatures = calculateDisplayFeatures(activity = context as Activity)
    )
}

@Composable
fun LeftPane(
    paddingValues: PaddingValues,
    viewModel: DashboardViewModel,
    navigateToTaskBoard: (String) -> Unit
) {
    Column(
        Modifier.padding(paddingValues)
    ) {
        Header(
            modifier = Modifier, title = "Starred Boards", onMenuItemClicked = {}
        )
        LazyVerticalGrid(
            modifier = Modifier
                .padding(top = 4.dp, bottom = 8.dp)
                .fillMaxHeight(),
            columns = Fixed(1),
            contentPadding = PaddingValues(4.dp),
        ) {
            items(viewModel.boardList.subList(0, 5)) { boardItem ->
                BoardCardComponent(
                    modifier = Modifier.clickable { navigateToTaskBoard("") },
                    title = boardItem.title,
                    backgroundImageUrl = boardItem.imageUrl
                )
            }
        }
    }
}

@Composable
fun RightPane(
    paddingValues: PaddingValues,
    viewModel: DashboardViewModel,
    navigateToTaskBoard: (String) -> Unit
) {
    Column(
        Modifier.padding(paddingValues)
    ) {
        Header(
            modifier = Modifier, title = "Trello workspace", showIcon = true
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 4.dp),
        ) {
            items(viewModel.boardList) {
                WorkshopCard(
                    modifier = Modifier.clickable { navigateToTaskBoard("") },
                    title = it.title,
                    imageUrl = it.imageUrl,
                    isWorkshopStarred = it.isStarred
                )
            }
        }
    }
}