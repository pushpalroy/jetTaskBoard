package com.jetapps.jettaskboard.model

data class ListModel(
  val id: Int? = null,
  val title: String,
  val cards: List<CardModel> = listOf()
)