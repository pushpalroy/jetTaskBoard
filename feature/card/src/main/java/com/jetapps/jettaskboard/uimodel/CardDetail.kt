package com.jetapps.jettaskboard.uimodel

data class CardDetail(
    val id: Int? = null,
    val boardId : Int? = null,
    val title: String? = null,
    val description: String? = null,
    val coverImageUrl: String? = null,
    val boardName: String? = null,
    val listName: String? = null,
    val authorName: String? = null,
    val startDate: String? = null,
    val dueDate: String? = null
)
