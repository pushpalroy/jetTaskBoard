package com.jetapps.jettaskboard.model

data class ListModel(
    val id: Int = 0,
    val title: String,
    val cards: MutableList<CardModel> = mutableListOf()
)
