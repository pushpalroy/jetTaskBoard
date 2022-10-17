package com.jetapps.jettaskboard

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateListOf // ktlint-disable import-ordering
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetapps.jettaskboard.model.Board
import com.jetapps.jettaskboard.model.BoardListModel
import com.jetapps.jettaskboard.util.BoardList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var toggleDrawerContent = mutableStateOf(true)

    private val _listOfBoards: MutableList<Board> = mutableStateListOf()
    val listOfBoards: List<Board> = _listOfBoards

    /**
     * A list of Boards to show on the dashboard
     */
    fun getBoardListData() {
        viewModelScope.launch {
            // Trigger repository requests in parallel
            val boardDeferred = async { getFakeBoardList() }

            // Wait for all requests to finish
            val boardList = boardDeferred.await().successOr(BoardListModel())
            if (_listOfBoards.isEmpty()) {
                _listOfBoards.addAll(boardList.listOfBoards)
            }
        }
    }

    private fun getFakeBoardList(): Result<BoardListModel> {
        return Result.Success(BoardList)
    }
}
