package com.jetapps.jettaskboard.state

import com.jetapps.jettaskboard.model.BoardModel
import com.jetapps.jettaskboard.model.ListModel

data class CardDetailState(
    val boards: List<BoardModel> = emptyList(),
    val lists: List<ListModel> = emptyList(),
    val cardDescription: String = "",
    val cardTitle: String = "",
)