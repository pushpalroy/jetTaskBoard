package com.jetapps.jettaskboard

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetapps.jettaskboard.model.Board
import com.jetapps.jettaskboard.model.ProfileModel
import com.jetapps.jettaskboard.usecase.dashboard.FetchAllBoardsUseCase
import com.jetapps.jettaskboard.usecase.dashboard.FetchProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val fetchAllBoardsUseCase: FetchAllBoardsUseCase,
    private val fetchProfileUseCase: FetchProfileUseCase,
) : ViewModel() {

    init {
        fetchProfile()
        getBoardListData()
    }

    var toggleDrawerContent = mutableStateOf(true)

    private val _listOfBoards: MutableStateFlow<List<Board>> = MutableStateFlow(emptyList())
    val listOfBoards: StateFlow<List<Board>> = _listOfBoards

    private val _profileFlow: MutableStateFlow<ProfileModel?> = MutableStateFlow(null)
    val profileFlow: StateFlow<ProfileModel?> = _profileFlow

    /**
     * A list of Boards to show on the dashboard
     */
    private fun getBoardListData() {
        viewModelScope.launch {
            fetchAllBoardsUseCase.invoke()
                .map { boardModelList ->
                    boardModelList.map { boardModel ->
                        Board(
                            title = boardModel.title,
                            imageUrl = "",
                            isStarred = false
                        )
                    }
                }
                .collect { boardList ->
                    _listOfBoards.emit(
                        boardList
                    )
                }
        }
    }

    private fun fetchProfile() {
        viewModelScope.launch {
            val profile = fetchProfileUseCase.invoke()
//            _profileFlow.emit(
//                profile
//            )
        }
    }
}
