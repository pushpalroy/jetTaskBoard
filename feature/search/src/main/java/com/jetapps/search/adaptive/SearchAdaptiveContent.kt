package com.jetapps.search.adaptive

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.util.BoardList
import com.jetapps.search.component.CardListResult
import com.jetapps.search.component.EmptySearchResultScreen
import com.jetapps.search.component.LazyVerticalGridBoardList
import com.jetapps.search.component.SearchChipGroup
import com.jetapps.search.component.SearchExpandedChipGroup
import com.jetapps.search.data.SearchType
import com.jetapps.search.data.getSelectedType
import com.jetapps.search.presentation.SearchScreenViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SearchAdaptiveContent(
    isScreenExpanded: Boolean = false,
    viewModel: SearchScreenViewModel,
    navigateUp: () -> Unit
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<String>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    val selectSearchType: MutableState<SearchType?> = remember {
        mutableStateOf(SearchType.BOARD_TYPE)
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                SearchMainPane(
                    viewModel = viewModel,
                    navigateToBoardScreen = {},
                    selectSearchType = selectSearchType,
                    navigateToCardScreen = {},
                    isExpandedWindow = isScreenExpanded
                )
            }
        },
        detailPane = {
            AnimatedPane {
                SearchDetailPane(
                    viewModel = viewModel,
                    selectSearchType = selectSearchType,
                    navigateToBoardScreen = {},
                    navigateToCardScreen = {},
                )
            }
        }
    )
}

@Composable
fun SearchMainPane(
    modifier: Modifier = Modifier,
    isExpandedWindow: Boolean = false,
    viewModel: SearchScreenViewModel,
    navigateToBoardScreen: (String) -> Unit,
    selectSearchType: MutableState<SearchType?>,
    navigateToCardScreen: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        if (isExpandedWindow) {
            ExpandedSearchView(viewModel)
            Spacer(modifier = Modifier.height(8.dp))
            SearchExpandedChipGroup(
                allSearchType = viewModel.getAllDefaultSearchOptions(),
                selectedCar = selectSearchType.value,
                onSelectedChanged = { selectedSearchType ->
                    selectSearchType.value = getSelectedType(selectedSearchType)
                }
            )
        } else {
            SearchChipGroup(
                allSearchType = viewModel.getAllDefaultSearchOptions(),
                selectedCar = selectSearchType.value,
                onSelectedChanged = { selectedSearchType ->
                    selectSearchType.value = getSelectedType(selectedSearchType)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            when (selectSearchType.value) {
                SearchType.BOARD_TYPE -> {
                    LazyVerticalGridBoardList(
                        boardList = BoardList.listOfBoards,
                        navigateToTaskBoard = { selectBoardId ->
                            navigateToBoardScreen(selectBoardId)
                        }
                    )
                }

                SearchType.CARD_TYPE -> {
                    CardListResult(
                        cardList = BoardList.listOfBoards,
                        navigateToTaskBoard = { selectedCardId ->
                            navigateToCardScreen(selectedCardId)
                        }
                    )
                }

                SearchType.MEMBER_TYPE -> {
                    EmptySearchResultScreen(showLoader = false) {}
                }

                SearchType.LABEL_TYPE -> {
                    EmptySearchResultScreen(showLoader = true) {}
                }

                SearchType.TICKET_TYPE -> {
                    EmptySearchResultScreen {}
                }

                null -> {
                    EmptySearchResultScreen {}
                }
            }
        }
    }
}
