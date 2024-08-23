package com.jetapps.jettaskboard

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.model.LabelModel
import com.jetapps.jettaskboard.state.CardDetailState
import com.jetapps.jettaskboard.theme.LabelBlue
import com.jetapps.jettaskboard.theme.LabelGreen
import com.jetapps.jettaskboard.theme.LabelOrange
import com.jetapps.jettaskboard.theme.LabelPeach
import com.jetapps.jettaskboard.theme.LabelViolet
import com.jetapps.jettaskboard.theme.LabelYellow
import com.jetapps.jettaskboard.uimodel.CardDetail
import com.jetapps.jettaskboard.uimodel.LabelColor
import com.jetapps.jettaskboard.usecase.dashboard.CreateCardUseCase
import com.jetapps.jettaskboard.usecase.dashboard.FetchAllBoardsUseCase
import com.jetapps.jettaskboard.usecase.dashboard.FetchAllListsRelatedToBoardUseCase
import com.jetapps.jettaskboard.usecase.dashboard.FetchAllListsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val fetchAllListsUseCase: FetchAllListsUseCase,
    private val fetchAllListsRelatedToBoardUseCase: FetchAllListsRelatedToBoardUseCase,
    private val fetchAllBoardsUseCase: FetchAllBoardsUseCase,
    private val createCardUseCase: CreateCardUseCase
) : ViewModel() {

    private val _cardDetailState: MutableStateFlow<CardDetailState> =
        MutableStateFlow(CardDetailState())
    val cardDetailState: StateFlow<CardDetailState> = _cardDetailState

    val cardModel by mutableStateOf(CardDetail(coverImageUrl = "fsd"))

    val imageAttachmentList = mutableStateListOf<Uri>()

    val labels = mutableStateListOf(
        LabelColor(LabelGreen),
        LabelColor(LabelYellow),
        LabelColor(LabelPeach),
        LabelColor(LabelOrange),
        LabelColor(LabelViolet),
        LabelColor(LabelBlue)
    )

    val selectedColors = mutableStateListOf<Color>()

    var isLabelRowClicked = mutableStateOf(false)

    var inputValue = mutableStateOf(TextFieldValue(cardModel.description ?: ""))

    val isTopText = mutableStateOf(false)

    val isBottomText = mutableStateOf(false)

    val startDateText = mutableStateOf(cardModel.startDate ?: "Start Date...")

    val dueDateText = mutableStateOf(cardModel.dueDate ?: "Due Date...")

    /**
     * Fetching all the boards when
     * Creating new Card from dashboard
     * Else Showing the select boardId
     */
    fun fetchBoards(boardId: Int?) {
        viewModelScope.launch {
            boardId?.let { id ->
                fetchAllBoardsUseCase.invoke().collect { boardList ->
                    _cardDetailState.value = _cardDetailState.value.copy(
                        boards = boardList
                    )
                    Timber.tag("TAG").d("BoardLists %s", boardList)
                }
            }
        }
    }

    /**
     * Fetching all the lists when
     * creating a new card from dashboard
     * else showing the list belongs to the following board only
     */
    fun fetchLists(boardId: Int?) {
        viewModelScope.launch {
            boardId?.let { id ->
                val lists = fetchAllListsRelatedToBoardUseCase.invoke(id)
                _cardDetailState.value = _cardDetailState.value.copy(
                    lists = lists
                )
                Timber.tag("TAG").d("Fetch Board related Lists: %s", lists)
            } ?: run {
                val allListsAvailable = fetchAllListsUseCase.invoke()
                _cardDetailState.value = _cardDetailState.value.copy(
                    lists = allListsAvailable
                )
                Timber.tag("TAG").d("Fetch All Lists: %s", allListsAvailable)
            }
        }
    }


    // Todo(Niket) : Provide a validator here to check for edge cases
    fun submitCard() {
        viewModelScope.launch {
            val cardModel = with(cardModel) {
                CardModel(
                    title = title ?: "Card title",
                    description = description,
                    coverImageUrl = coverImageUrl,
                    labels = labels.toList().map { color ->
                        LabelModel(
                            labelName = "Name : ${color.color}",
                            labelColor = color.color.toArgb().toLong()
                        )
                    },
                    boardId = boardId ?: 0,
                    listId = 0,
                )
            }
            Timber.tag("TAG").d("CardModel %s", cardModel)
            createCardUseCase.invoke(cardModel)
        }
    }
}
