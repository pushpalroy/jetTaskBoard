package com.jetapps.jettaskboard.createboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetapps.jettaskboard.model.BoardModel
import com.jetapps.jettaskboard.usecase.dashboard.CreateBoardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateBoardViewModel @Inject constructor(
    private val createBoardUseCase: CreateBoardUseCase
) : ViewModel() {

    companion object {
        val visibilityList = mapOf(
            "Workspace" to "Anyone to the users Workspace can see this board",
            "Public" to "The board is public. It's visible to anyone with the link and will show up to anyone with google link",
            "Private" to "the board is private. Only people added to the board can view and edit it"
        )
    }

    fun createNewBoard(boardModel: BoardModel) {
        viewModelScope.launch {
            createBoardUseCase.invoke(boardModel)
        }
    }
}