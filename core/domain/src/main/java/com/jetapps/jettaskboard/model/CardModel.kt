package com.jetapps.jettaskboard.model

data class CardModel(
  val id: Int? = null,
  val title: String,
  val description: String? = null,
  val coverImageUrl: String? = null,
  val boardId: String? = null,
  val listId: String? = null,
  val authorId: String? = null,
  val startDate: String? = null,
  val dueDate: String? = null
)