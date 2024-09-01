package com.jetapps.jettaskboard.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetapps.jettaskboard.board.ExpandedBoardDrawerState
import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.model.ListModel
import com.jetapps.jettaskboard.usecase.board.CreateCardUseCase
import com.jetapps.jettaskboard.usecase.board.CreateNewListUseCase
import com.jetapps.jettaskboard.usecase.board.DeleteCardUseCase
import com.jetapps.jettaskboard.usecase.board.GetBoardDetailsUseCase
import com.jetapps.jettaskboard.usecase.board.GetLatestBackgroundImgUrlUseCase
import com.jetapps.jettaskboard.usecase.board.UpdateCardUseCase
import com.jetapps.jettaskboard.util.Board
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TaskBoardViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getLatestBackgroundImgUrlUseCase: GetLatestBackgroundImgUrlUseCase,
    private val getBoardDetailsUseCase: GetBoardDetailsUseCase,
    private val createCardUseCase: CreateCardUseCase,
    private val deleteCardUseCase: DeleteCardUseCase,
    private val createNewListUseCase: CreateNewListUseCase,
) : ViewModel() {

    private val passedBoardId = savedStateHandle.get<Long>("boardId")
        get() = field

    /**
     * Defines a list of [ListModel]s which has a list of [CardModel]s internally.
     * The list of [ListModel]s is used to setup the row of lists (eg: To-do, InProgress, Completed, etc.) in the [Board].
     * The list of [CardModel]s inside every list, is used to setup the column of cards in a particular list.
     */
    private val _lists: MutableStateFlow<List<ListModel>> = MutableStateFlow(emptyList())
    val lists: StateFlow<List<ListModel>> = _lists

    var boardTitle by mutableStateOf("Board Dummy Title")
    var latestBackgroundImgUri by mutableStateOf("")

    private var _drawerScreenState = mutableStateOf(ExpandedBoardDrawerState.DRAWER_SCREEN_STATE)
    val drawerScreenState: State<ExpandedBoardDrawerState> = _drawerScreenState

    init {
        getBoardData()
        getLatestBackgroundImgUri()
    }

    /**
     * Get the cached latest Board Background Img URI
     */
    private fun getLatestBackgroundImgUri() = viewModelScope.launch {
        getLatestBackgroundImgUrlUseCase.invoke().collectLatest { imageUri ->
            imageUri?.let { safeImageUri ->
                latestBackgroundImgUri = safeImageUri
            }
        }
    }

    /**
     * A Board has a list of Lists: Board = f(List)
     * A List has a list of Cards: List = f(Card)
     * A new Card has to be inserted in the list Cards of a Board
     */
    private fun getBoardData() {
        println("Test Passed Id $passedBoardId")
        viewModelScope.launch {
            passedBoardId?.let { safeId ->
                getBoardDetailsUseCase.invoke(safeId)
                    .distinctUntilChanged()
                    .collect { result ->
                        println("Test getBoardData $result")
                        Timber.tag("DAO").d("getBoardData: %s", result)
                        boardTitle = result?.boardTitle ?: ""
                        result?.listModel?.let { boardLists ->
                            _lists.emit(
                                boardLists
                            )
                        }
                    }
            }
        }
    }

    fun addNewCardInList(listId: Long) {
        viewModelScope.launch {
            createCardUseCase.invoke(
                CardModel(
                    title = "New Card",
                    listId = listId,
                    description = "",
                    boardId = passedBoardId ?: 0,
                    authorId = ""
                ),
            )
        }
    }

    fun moveCardToDifferentList(
        cardId: Int,
        oldListId: Int,
        newListId: Int
    ) {
        viewModelScope.launch {
            // Locate the card to be moved
            val cardToMove =
                _lists.value.find { it.listId?.toInt() == oldListId }?.cards?.find { it.id.toInt() == cardId }

            // Removing a card from one list and adding to a new list
            // Basically, modifying the internal data of two list-items simultaneously
            cardToMove?.let { safeCard ->
                _lists.value.find { it.listId?.toInt() == oldListId }?.cards?.removeIf { it.id == safeCard.id }
                _lists.value.find { it.listId?.toInt() == newListId }?.cards?.add(
                    safeCard.copy(
                        listId = newListId.toLong()
                    )
                )
            }
        }
    }

    fun addNewList(listTitle: String) {
        val model = ListModel(
            title = listTitle,
            boardId = passedBoardId
        )
        viewModelScope.launch {
            createNewListUseCase.invoke(
                model
            )
        }
    }

    fun changeExpandedScreenState(newState: ExpandedBoardDrawerState) {
        _drawerScreenState.value = newState
    }
}
