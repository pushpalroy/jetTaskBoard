package com.jetapps.jettaskboard.state

import androidx.compose.ui.graphics.Color
import com.jetapps.jettaskboard.model.BoardModel
import com.jetapps.jettaskboard.model.ListModel

data class CreateCardStates(
    val boards: Map<Color, BoardModel> = emptyMap(),
    val lists: Map<String, ListModel> = emptyMap(),
    val selectedBoardModel: BoardModel? = null,
    val selectedListFromBoard: ListModel? = null,
    val cardTitle: String? = null,
    val cardDescription: String? = null
)