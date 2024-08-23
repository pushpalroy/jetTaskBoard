package com.jetapps.jettaskboard.util

import com.jetapps.jettaskboard.model.Board
import com.jetapps.jettaskboard.model.BoardListModel

//TODO(Niket): remove this file.

/**
 * All methods here are temporary for Demonstration
 * Purpose
 */
val ListOfBoards by lazy {
    listOf(
        Board("Praxis", ImageUrls[0], true),
        Board("Discord Clone", ImageUrls[1]),
        Board("Trello Workspace", ImageUrls[2], true),
        Board("Praxis Flutter", ImageUrls[3], true),
        Board("JetTaskBoard", ImageUrls[4]),
        Board("Google Play Clone", ImageUrls[5]),
        Board("JetFlix", ImageUrls[6], true)
    )
}

private val ImageUrls by lazy {
    listOf(
        "https://images.unsplash.com/photo-1508264165352-258db2ebd59b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=776&q=80",
        "https://images.unsplash.com/photo-1506792006437-256b665541e2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80",
        "https://images.unsplash.com/photo-1500042600524-37ecb686c775?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80",
        "https://images.unsplash.com/photo-1514463439976-96c5d33f0d57?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1769&q=80",
        "https://images.unsplash.com/photo-1503149779833-1de50ebe5f8a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80",
        "https://images.unsplash.com/photo-1531176175280-33e81422832a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80",
        "https://images.unsplash.com/photo-1657571484167-9975a442ecbb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1874&q=80"
    )
}

val BoardList = BoardListModel(
    listOfBoards = ListOfBoards.toMutableList()
)
