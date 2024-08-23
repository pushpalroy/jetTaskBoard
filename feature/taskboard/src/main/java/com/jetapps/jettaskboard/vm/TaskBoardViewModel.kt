package com.jetapps.jettaskboard.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import javax.inject.Inject

@HiltViewModel
class TaskBoardViewModel @Inject constructor(
    private val getLatestBackgroundImgUrlUseCase: GetLatestBackgroundImgUrlUseCase,
    private val getBoardDetailsUseCase: GetBoardDetailsUseCase,
    private val createCardUseCase: CreateCardUseCase,
    private val updateCardUseCase: UpdateCardUseCase,
    private val deleteCardUseCase: DeleteCardUseCase,
    private val createNewListUseCase: CreateNewListUseCase,
) : ViewModel() {

    /**
     * Defines a list of [ListModel]s which has a list of [CardModel]s internally.
     * The list of [ListModel]s is used to setup the row of lists (eg: To-do, InProgress, Completed, etc.) in the [Board].
     * The list of [CardModel]s inside every list, is used to setup the column of cards in a particular list.
     */
    private val _lists: MutableStateFlow<List<ListModel>> = MutableStateFlow(emptyList())
    val lists: StateFlow<List<ListModel>> = _lists

    /**
     * A counter of the total number of cards in the board, maintained to assign a unique
     * index everytime a new card gets added to the board in a particular list, which in also
     * used to run recomposition whenever the new card is added.
     */
    private var totalCards by mutableIntStateOf(0)
    var boardId by mutableIntStateOf(0)
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
        viewModelScope.launch {
            getBoardDetailsUseCase.invoke(boardId)
                .distinctUntilChanged()
                .collect { result ->
                    println(result)
                    boardTitle = result.boardTitle
                    // Executed for once when ui is loaded
                    _lists.emit(
                        result.listModel
                    )
                }
        }
    }

    fun addNewCardInList(listId: Int) {
        viewModelScope.launch {
            totalCards = 0
            updateTheTotalCardCount(_lists.value.size + 1)
            createCardUseCase.invoke(
                CardModel(
                    id = totalCards,
                    title = "New Card",
                    listId = listId,
                    description = "",
                    coverImageUrl = "",
                    labels = emptyList(),
                    boardId = boardId,
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
                _lists.value.find { it.listId == oldListId }?.cards?.find { it.id == cardId }

            // Removing a card from one list and adding to a new list
            // Basically, modifying the internal data of two list-items simultaneously
            cardToMove?.let { safeCard ->
                _lists.value.find { it.listId == oldListId }?.cards?.removeIf { it.id == safeCard.id }
                _lists.value.find { it.listId == newListId }?.cards?.add(safeCard.copy(listId = newListId))
            }
        }
    }

    fun addNewList() {
        val model = ListModel(listId = _lists.value.size + 1, title = "New List", boardId = boardId)
        viewModelScope.launch {
            createNewListUseCase.invoke(
                model
            )
        }
    }

    fun changeExpandedScreenState(newState: ExpandedBoardDrawerState) {
        _drawerScreenState.value = newState
    }

    private fun updateTheTotalCardCount(newValue: Int) {
        totalCards = newValue
    }
}
