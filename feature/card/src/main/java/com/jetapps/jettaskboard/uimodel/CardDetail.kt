package com.jetapps.jettaskboard.uimodel

data class CardDetail(
    val id: Long? = null,
    val boardId: Long? = null,
    val title: String? = null,
    val description: String? = null,
    val coverImageUrl: String? = "https://images.unsplash.com/photo-1574169208507-84376144848b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=400&q=80",
    val boardName: String? = null,
    val listName: String? = null,
    val authorName: String? = null,
    val startDate: String? = null,
    val dueDate: String? = null
)
