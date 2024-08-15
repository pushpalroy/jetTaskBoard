package com.jetapps.search.adaptive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.util.BoardList
import com.jetapps.search.component.CardListResult
import com.jetapps.search.component.EmptySearchResultScreen
import com.jetapps.search.component.LazyVerticalGridBoardList
import com.jetapps.search.component.SearchExpandedChipGroup
import com.jetapps.search.data.SearchType
import com.jetapps.search.data.getSelectedType
import com.jetapps.search.presentation.SearchScreenViewModel

@Composable
fun SearchLeftPane(
    modifier: Modifier = Modifier,
    viewModel: SearchScreenViewModel,
    navigateUp: () -> Unit,
    selectSearchType: MutableState<SearchType?>
) {
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        ExpandedSearchView(viewModel)
        Spacer(modifier = Modifier.height(8.dp))
        SearchExpandedChipGroup(
            allSearchType = viewModel.getAllDefaultSearchOptions(),
            selectedCar = selectSearchType.value,
            onSelectedChanged = { selectedSearchType ->
                selectSearchType.value = getSelectedType(selectedSearchType)
            }
        )
    }
}

@Composable
fun ExpandedSearchView(
    viewModel: SearchScreenViewModel
) {
    Surface(
        modifier = Modifier
            .padding(all = 4.dp),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colors.onSecondary.copy(alpha = 0.25f)
    ) {
        TextField(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth(),
            value = viewModel.inputValue.value,
            onValueChange = {
                viewModel.inputValue.value = it
            },
            placeholder = {
                Text(text = "Search..")
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun SearchDetailPane(
    viewModel: SearchScreenViewModel,
    selectSearchType: MutableState<SearchType?>,
    navigateToBoardScreen: (String) -> Unit,
    navigateToCardScreen: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
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
