package com.jetapps.jettaskboard.repo

import com.jetapps.jettaskboard.model.BoardModel
import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.model.ListModel
import com.jetapps.jettaskboard.model.ProfileModel
import kotlinx.coroutines.flow.Flow

interface DashboardRepo {
    suspend fun fetchAllBoards() : Flow<List<BoardModel>>
    suspend fun fetchProfile() : ProfileModel
    suspend fun createCard(cardModel: CardModel)
    suspend fun createBoard(boardModel: BoardModel)
    suspend fun fetchAllLists() : List<ListModel>
    suspend fun fetchListsFromRelatedBoard(boardId : Long) : List<ListModel>
}