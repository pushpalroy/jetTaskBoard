package com.jetapps.jettaskboard.model

data class CardModel(
    val id: Int = 0,
    val title: String,
    val description: String? = null,
    val coverImageUrl: String? = null,
    val labels: List<LabelModel> = listOf(),
    val boardId: Int,
    val listId: Int,
    val authorId: String? = null,
    val startDate: String? = null,
    val dueDate: String? = null
)
