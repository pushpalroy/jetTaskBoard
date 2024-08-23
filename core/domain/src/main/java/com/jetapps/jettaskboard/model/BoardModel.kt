package com.jetapps.jettaskboard.model

data class BoardModel(
    val id: Int? = null,
    val title: String = "",
    val lists: List<ListModel> = emptyList(),
    val isFav: Boolean = false,
)

data class BoardWithListAndCard(
    val boardId : Int,
    val boardTitle : String,
    val isFav: Boolean,
    val listModel: List<ListModel>,
)