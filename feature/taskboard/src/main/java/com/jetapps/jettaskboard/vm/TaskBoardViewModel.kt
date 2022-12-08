package com.jetapps.jettaskboard.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue // ktlint-disable import-ordering
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetapps.jettaskboard.Result
import com.jetapps.jettaskboard.board.ExpandedBoardDrawerState
import com.jetapps.jettaskboard.change_bg.ChangeBackgroundScreenState
import com.jetapps.jettaskboard.model.BoardModel
import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.model.ListModel
import com.jetapps.jettaskboard.successOr
import com.jetapps.jettaskboard.usecase.board.GetLatestBackgroundImgUrlUseCase
import com.jetapps.jettaskboard.util.Board
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskBoardViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getLatestBackgroundImgUrlUseCase: GetLatestBackgroundImgUrlUseCase
) : ViewModel() {

    /**
     * Holds the information of the [Board], a pair of ID and title.
     */
    var boardInfo = mutableStateOf<Pair<Int?, String>>(Pair(null, ""))
        private set

    /**
     * Defines a list of [ListModel]s which has a list of [CardModel]s internally.
     * The list of [ListModel]s is used to setup the row of lists (eg: To-do, InProgress, Completed, etc.) in the [Board].
     * The list of [CardModel]s inside every list, is used to setup the column of cards in a particular list.
     */
    private val _lists = mutableStateListOf<ListModel>()
    val lists: List<ListModel> = _lists

    /**
     * A counter of the total number of cards in the board, maintained to assign a unique
     * index everytime a new card gets added to the board in a particular list, which in also
     * used to run recomposition whenever the new card is added.
     */
    var totalCards by mutableStateOf(0)
    var latestBackgroundImgUri by mutableStateOf("")

    private var _drawerScreenState = mutableStateOf(ExpandedBoardDrawerState.DRAWER_SCREEN_STATE)
    val drawerScreenState: State<ExpandedBoardDrawerState> = _drawerScreenState

    init {
        getLatestBackgroundImgUri()
    }

    /**
     * Get the cached latest Board Background Img URI
     */
    private fun getLatestBackgroundImgUri() = viewModelScope.launch {
        delay(3000)
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
    fun getBoardData() {
        viewModelScope.launch {
            // Trigger repository requests in parallel
            val boardDeferred = async { getFakeBoard() }

            // Wait for all requests to finish
            val board = boardDeferred.await().successOr(BoardModel())

            boardInfo.value = Pair(board.id, board.title)

            // Executed for once when ui is loaded
            if (_lists.isEmpty()) {
                _lists.addAll(board.lists)
                _lists.forEach { list ->
                    totalCards += list.cards.size
                }
            }
        }
    }

    fun addNewCardInList(listId: Int) {
        viewModelScope.launch {
            totalCards += 1
            val newCardIndex = totalCards
            _lists.find { it.id == listId }?.cards?.add(
                CardModel(id = newCardIndex, title = "New Card", listId = listId)
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
            val cardToMove = _lists.find { it.id == oldListId }?.cards?.find { it.id == cardId }

            // Removing a card from one list and adding to a new list
            // Basically, modifying the internal data of two list-items simultaneously
            cardToMove?.let { safeCard ->
                _lists.find { it.id == oldListId }?.cards?.removeIf { it.id == safeCard.id }
                _lists.find { it.id == newListId }?.cards?.add(safeCard.copy(listId = newListId))
            }
        }
    }

    fun addNewList() {
        _lists.add(
            ListModel(id = _lists.size + 1, title = "New List")
        )
    }

    private fun getFakeBoard(): Result<BoardModel> {
        return Result.Success(Board)
    }

    fun changeExpandedScreenState(newState: ExpandedBoardDrawerState) {
        _drawerScreenState.value = newState
    }
}
