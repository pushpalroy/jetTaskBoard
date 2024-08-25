package com.jetapps.jettaskboard.model


const val YellowLabel = 0xFFFBC02D
const val BlueLabel = 0xFF03A9F4
const val RedLabel = 0xFFFF8A80
const val GreenLabel = 0xFF8BC34A
const val OrangeLabel = 0xFFFFD180

val labelModelList = listOf(
    LabelModel(
        id = "0",
        labelName = "Architecture",
        labelColor = YellowLabel
    ),
    LabelModel(
        id = "2",
        labelName = "Tests",
        labelColor = GreenLabel
    ),
    LabelModel(
        id = "3",
        labelName = "CI/CD",
        labelColor = RedLabel
    ),
    LabelModel(
        id = "4",
        labelName = "Library",
        labelColor = OrangeLabel
    )
)

data class CardModel(
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val coverImageUrl: String? = "https://images.unsplash.com/photo-1657571484167-9975a442ecbb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1874&q=80",
    val labels: List<LabelModel> = labelModelList,
    val boardId: Long,
    val listId: Long,
    val authorId: String? = null,
    val startDate: String? = null,
    val dueDate: String? = null
)

