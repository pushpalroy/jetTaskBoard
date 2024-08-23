package com.jetapps.jettaskboard.model

data class ListModel(
    val listId: Int?,
    val boardId : Int,
    val title: String,
    val cards: MutableList<CardModel> = mutableListOf()
)
