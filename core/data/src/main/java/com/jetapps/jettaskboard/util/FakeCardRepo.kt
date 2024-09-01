package com.jetapps.jettaskboard.util

import com.jetapps.jettaskboard.model.BoardModel
import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.model.LabelModel
import com.jetapps.jettaskboard.model.ListModel

const val YellowLabel = 0xFFFBC02D
const val BlueLabel = 0xFF03A9F4
const val RedLabel = 0xFFFF8A80
const val GreenLabel = 0xFF8BC34A
const val OrangeLabel = 0xFFFFD180

val Labels by lazy {
    listOf(
        LabelModel(
            id = "0",
            labelName = "Architecture",
            labelColor = YellowLabel
        ),
        LabelModel(
            id = "1",
            labelName = "Feature",
            labelColor = BlueLabel
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
}

val CardList_1 by lazy {
    listOf(
        CardModel(
            id = 1,
            title = "Add Compose UI Tests",
            description = "Need to test for compose ui for various scenarios. Tests are an amazing way to find flexibility in UI and catch early bugs.",
            coverImageUrl = "https://images.unsplash.com/photo-1535350356005-fd52b3b524fb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=400&q=80",
            labels = listOf(Labels[1], Labels[2]),
            boardId = 1,
            listId = 0,
            authorId = null
        ),
        CardModel(
            id = 2,
            title = "Add Compose Animations",
            coverImageUrl = "",
            labels = listOf(Labels[0], Labels[1]),
            boardId = 2,
            listId = 0,
            authorId = null
        ),
        CardModel(
            id = 3,
            title = "Add Home Screen",
            description = "Need add to home screen which will show a list of items in a grid view.",
            coverImageUrl = "",
            labels = listOf(Labels[1]),
            boardId = 3,
            listId = 0,
            authorId = null
        ),
        CardModel(
            id = 4,
            title = "Add Board screen",
            description = "Need to add a Board Screen.",
            coverImageUrl = "",
            labels = listOf(Labels[0], Labels[1], Labels[2]),
            boardId = 4,
            listId = 0,
            authorId = null
        )
    )
}

val CardList_2 by lazy {
    listOf(
        CardModel(
            id = 5,
            title = "Android project setup",
            coverImageUrl = "https://images.unsplash.com/photo-1574169208507-84376144848b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=400&q=80",
            labels = listOf(Labels[0], Labels[1], Labels[3]),
            boardId = 1,
            listId = 1,
            authorId = null
        ),
        CardModel(
            id = 6,
            title = "Implement DI using Hilt",
            description = "Need to implement hilt.",
            coverImageUrl = "",
            labels = listOf(Labels[0], Labels[1]),
            boardId = 2,
            listId = 1,
            authorId = null
        ),
        CardModel(
            id = 7,
            title = "Adaptive UI using Compose",
            description = "Need to add adaptive UI using compose.",
            coverImageUrl = "",
            labels = listOf(Labels[1]),
            boardId = 3,
            listId = 1,
            authorId = null
        )
    )
}

val CardList_3 by lazy {
    listOf(
        CardModel(
            id = 8,
            title = "Add Splash Screen",
            coverImageUrl = "https://images.unsplash.com/photo-1519751138087-5bf79df62d5b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=400&q=80",
            labels = listOf(Labels[1]),
            boardId = 1,
            listId = 2,
            authorId = null
        ),
        CardModel(
            id = 9,
            title = "Add Github Actions",
            description = "Need to integrate Github Actions for CI/CD.",
            coverImageUrl = "",
            labels = listOf(Labels[0], Labels[1], Labels[2]),
            boardId = 2,
            listId = 2,
            authorId = null
        ),
        CardModel(
            id = 10,
            title = "Add Card edit screen",
            description = "Need to add edit Card screen.",
            coverImageUrl = "",
            labels = listOf(Labels[1]),
            boardId = 3,
            listId = 2,
            authorId = null
        )
    )
}

val Board = BoardModel(
    title = "JetTaskBoard",
    lists = listOf(
        ListModel(
            listId = 0,
            title = "Backlog",
            cards = CardList_1.toMutableList(),
            boardId = 0
        ),
        ListModel(
            listId = 1,
            title = "In progress",
            cards = CardList_2.toMutableList(),
            boardId = 0
        ),
        ListModel(
            listId = 2,
            title = "Completed",
            cards = CardList_3.toMutableList(),
            boardId = 0
        )
    )
)
