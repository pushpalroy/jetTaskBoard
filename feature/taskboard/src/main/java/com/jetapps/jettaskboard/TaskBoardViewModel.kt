package com.jetapps.jettaskboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetapps.jettaskboard.model.BoardModel
import com.jetapps.jettaskboard.model.ListModel
import com.jetapps.jettaskboard.util.Board
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskBoardViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle
) : ViewModel() {

  var boardInfo = mutableStateOf<Pair<Int?, String>>(Pair(null, ""))
    private set

  private val _lists: MutableList<ListModel> = mutableStateListOf()
  val lists: List<ListModel> by mutableStateOf(_lists, neverEqualPolicy())

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
      if (_lists.isEmpty()) {
        _lists.addAll(board.lists)
      }
    }
  }

  fun addNewCardInList(listId: Int) {
    viewModelScope.launch {
      // _lists.find { it.id == listId }?.cards?.add(
      //   CardModel(id = _lists.size + 1, title = "New Card", listId = listId)
      // )
    }
  }

  fun moveCardToDifferentList(
    cardId: Int,
    oldListId: Int,
    newListId: Int
  ) {
    viewModelScope.launch {
      val cardToMove = _lists.find { it.id == oldListId }?.cards?.find { it.id == cardId }

      cardToMove?.let { safeCard ->
        _lists.find { it.id == oldListId }?.cards?.removeIf { it.id == safeCard.id }
        _lists.find { it.id == newListId }?.cards?.add(safeCard.copy(listId = newListId))
      }
    }
  }

  fun addNewList() {
    _lists.add(
      ListModel(title = "New List")
    )
  }

  private fun getFakeBoard(): Result<BoardModel> {
    return Result.Success(Board)
  }
}