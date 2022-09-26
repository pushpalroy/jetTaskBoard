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

val Labels = listOf(
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
  ),
)

val CardList_1 = listOf(
  CardModel(
    id = 1,
    title = "Add Compose UI Tests",
    description = "Need to test for compose ui.",
    coverImageUrl = "",
    labels = listOf(Labels[1], Labels[2]),
    boardId = "1",
    listId = "1",
    authorId = null
  ),
  CardModel(
    id = 2,
    title = "Add Compose Animations",
    description = "Need to animations for Compose.",
    coverImageUrl = "",
    labels = listOf(Labels[0], Labels[1]),
    boardId = "1",
    listId = "1",
    authorId = null
  ),
  CardModel(
    id = 3,
    title = "Add Home Screen",
    description = "Need to home screen.",
    coverImageUrl = "",
    labels = listOf(Labels[1]),
    boardId = "1",
    listId = "1",
    authorId = null
  ),
  CardModel(
    id = 4,
    title = "Add Board screen",
    description = "Need to add a Board Screen.",
    coverImageUrl = "",
    labels = listOf(Labels[0], Labels[1], Labels[2]),
    boardId = "1",
    listId = "1",
    authorId = null
  ),
  CardModel(
    id = 5,
    title = "Add Card edit screen",
    description = "Need to add edit Card screen.",
    coverImageUrl = "",
    labels = listOf(Labels[1]),
    boardId = "1",
    listId = "1",
    authorId = null
  ),
)

val CardList_2 = listOf(
  CardModel(
    id = 6,
    title = "Android project setup",
    description = "Need to setup project.",
    coverImageUrl = "",
    labels = listOf(Labels[0], Labels[1], Labels[3]),
    boardId = "1",
    listId = "1",
    authorId = null
  ),
  CardModel(
    id = 7,
    title = "Implement DI using Hilt",
    description = "Need to implement hilt.",
    coverImageUrl = "",
    labels = listOf(Labels[0], Labels[1]),
    boardId = "1",
    listId = "1",
    authorId = null
  ),
  CardModel(
    id = 8,
    title = "Adaptive UI using Compose",
    description = "Need to add adaptive UI using compose.",
    coverImageUrl = "",
    labels = listOf(Labels[1]),
    boardId = "1",
    listId = "1",
    authorId = null
  )
)

val CardList_3 = listOf(
  CardModel(
    id = 9,
    title = "Add Splash Screen",
    description = "Need to add a splash screen.",
    coverImageUrl = "",
    labels = listOf(Labels[1]),
    boardId = "1",
    listId = "1",
    authorId = null
  ),
  CardModel(
    id = 10,
    title = "Add Github Actions",
    description = "Need to integrate Github Actions for CI/CD.",
    coverImageUrl = "",
    labels = listOf(Labels[0], Labels[1], Labels[2]),
    boardId = "1",
    listId = "1",
    authorId = null
  ),
)

val Board = BoardModel(
  title = "JetTaskBoard",
  lists = listOf(
    ListModel(
      title = "Backlog",
      cards = CardList_1
    ),
    ListModel(
      title = "In progress",
      cards = CardList_2
    ),
    ListModel(
      title = "Completed",
      cards = CardList_3
    )
  )
)