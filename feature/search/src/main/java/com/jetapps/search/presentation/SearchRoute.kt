package com.jetapps.search.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetapps.search.adaptive.SearchAdaptiveContent
import com.jetapps.search.component.SearchTopAppBar

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchScreenViewModel = hiltViewModel(),
    isExpandedScreen: Boolean,
    onNavigateUp: () -> Unit,
    navigateToBoardScreen: (String) -> Unit,
    navigateToCardScreen: (String) -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    androidx.compose.material.Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = {
            if (isExpandedScreen.not()) {
                SearchTopAppBar(
                    onCancelClick = {
                        onNavigateUp()
                    },
                    searchQueryEntered = { query ->
                        viewModel.searchAtDB(query)
                    },
                    searchScreenViewModel = viewModel
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            if (isExpandedScreen) {
                FloatingActionButton(onClick = { onNavigateUp() }) {
                    Icon(
                        modifier = Modifier
                            .padding(8.dp),
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "navigate_up"
                    )
                }
            }
        }
    ) { scaffoldPadding ->
        Row(
            modifier =
            modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
        ) {
            SearchAdaptiveContent(
                isScreenExpanded = isExpandedScreen,
                viewModel = viewModel,
                navigateUp = {
                    onNavigateUp()
                }
            )
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchRoute(
        navigateToBoardScreen = {},
        navigateToCardScreen = {},
        isExpandedScreen = false,
        onNavigateUp = {}
    )
}
