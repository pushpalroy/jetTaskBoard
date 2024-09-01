package com.jetapps.jettaskboard.model

data class ListModel(
    val listId: Long? = null,
    val boardId : Long?,
    val title: String?,
    val cards: MutableList<CardModel>? = mutableListOf()
)
