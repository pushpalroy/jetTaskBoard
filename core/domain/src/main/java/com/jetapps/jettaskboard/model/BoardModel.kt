package com.jetapps.jettaskboard.model

data class BoardModel(
  val id: Int? = null,
  val title: String = "",
  val lists: List<ListModel> = listOf()
)