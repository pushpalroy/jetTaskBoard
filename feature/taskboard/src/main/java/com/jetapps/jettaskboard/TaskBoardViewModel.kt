package com.jetapps.jettaskboard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jetapps.jettaskboard.model.BoardModel
import com.jetapps.jettaskboard.util.Board
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskBoardViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle
) : ViewModel() {

  fun getFakeBoardData() = BoardModel(
    id = Board.id,
    title = Board.title,
    lists = Board.lists
  )
}