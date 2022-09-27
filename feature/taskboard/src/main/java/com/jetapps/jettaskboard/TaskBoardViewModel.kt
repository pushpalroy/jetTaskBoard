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

  var boardList = mutableStateListOf<ListModel>()
    private set

  fun getBoardList() {
    viewModelScope.launch {
      with(getFakeBoardData()) {
        boardInfo.value = Pair(id, title)
        if (boardList.isEmpty()) {
          boardList.addAll(lists)
        }
      }
    }
  }

  fun insertNewCardInList(listId: Int) {
    boardList[listId].cards.add(CardModel(title = "New Card"))
  }

  private fun getFakeBoardData() = BoardModel(
    id = Board.id,
    title = Board.title,
    lists = Board.lists
  )
}