package com.jetapps.jettaskboard.feature_search.adaptive

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.feature_search.component.CardListResult
import com.jetapps.jettaskboard.feature_search.component.EmptySearchResultScreen
import com.jetapps.jettaskboard.feature_search.component.LazyVerticalGridBoardList
import com.jetapps.jettaskboard.feature_search.component.SearchChipGroup
import com.jetapps.jettaskboard.feature_search.data.SearchType
import com.jetapps.jettaskboard.feature_search.data.getSelectedType
import com.jetapps.jettaskboard.feature_search.presentation.SearchScreenViewModel
import com.jetapps.jettaskboard.util.BoardList

@Composable
fun SearchSinglePaneContent(
    viewModel : SearchScreenViewModel,
    navigateToBoardScreen : (String) -> Unit,
    selectSearchType : MutableState<SearchType?>,
    navigateToCardScreen: (String) -> Unit,
) {
    Column(
        modifier = androidx.compose.ui.Modifier
    ) {
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
                EmptySearchResultScreen {}
            }

            SearchType.LABEL_TYPE -> {
                EmptySearchResultScreen {}
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