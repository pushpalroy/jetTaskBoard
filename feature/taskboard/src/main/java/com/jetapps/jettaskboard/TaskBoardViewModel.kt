package com.jetapps.jettaskboard

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetapps.jettaskboard.model.BoardModel
import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.model.ListModel
import com.jetapps.jettaskboard.util.Board
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskBoardViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle
) : ViewModel() {

  var boardInfo = mutableStateOf<Pair<Int?, String>>(Pair(null, ""))
    private set

  private val _lists: MutableList<ListModel> = mutableStateListOf()
  val lists: List<ListModel> = _lists

  private val _cards: MutableList<CardModel> = mutableStateListOf()
  val cards: List<CardModel> = _cards

  /**
   * A Board has a list of Lists
   * A List has a list of Cards
   * A new Card has to be inserted in the list Cards of a Board
   */
  fun getBoardData() {
    viewModelScope.launch {
      with(getFakeBoardData()) {
        boardInfo.value = Pair(id, title)
        if (_lists.isEmpty()) {
          _lists.addAll(lists)
        }
        if (_cards.isEmpty()) {
          for (list in lists) {
            _cards.addAll(list.cards)
          }
        }
      }
    }
  }

  fun addNewCardInList(listId: Int) {
    viewModelScope.launch {
      _cards.add(
        CardModel(title = "New Card", listId = listId)
      )
    }
  }

  fun moveCardToDifferentList(
    cardId: Int,
    listId: Int
  ) {
    viewModelScope.launch {
      val tempCard = cards[cardId]
      _cards.removeAt(cardId)
      _cards.add(tempCard.copy(listId = listId))
    }
  }

  fun addNewList() {
    _lists.add(
      ListModel(title = "New List")
    )
  }

  private fun getFakeBoardData() = BoardModel(
    id = Board.id,
    title = Board.title,
    lists = Board.lists
  )
}