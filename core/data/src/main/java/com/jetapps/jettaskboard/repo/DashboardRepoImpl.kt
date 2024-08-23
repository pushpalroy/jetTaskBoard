package com.jetapps.jettaskboard.repo

import com.jetapps.jettaskboard.local.source.DatabaseSource
import com.jetapps.jettaskboard.mapper.BoardMapper
import com.jetapps.jettaskboard.mapper.CardMapper
import com.jetapps.jettaskboard.mapper.ListMapper
import com.jetapps.jettaskboard.model.BoardModel
import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.model.ListModel
import com.jetapps.jettaskboard.model.ProfileModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DashboardRepoImpl @Inject constructor(
    private val databaseSource: DatabaseSource,
    private val boardMapper: BoardMapper,
    private val cardMapper: CardMapper,
    private val listMapper: ListMapper,
) : DashboardRepo {
    override suspend fun fetchAllBoards(): Flow<List<BoardModel>> {
        return databaseSource.getBoards().map { source ->
                source.map { model ->
                    boardMapper.mapToData(model)
                }
            }
    }

    override suspend fun fetchProfile(): ProfileModel {
        return ProfileModel()
    }

    override suspend fun createCard(cardModel: CardModel) {
        databaseSource.createCard(cardMapper.mapToData(cardModel))
    }

    override suspend fun createBoard(boardModel: BoardModel) {
        withContext(Dispatchers.IO) {
            databaseSource.createBoard(boardMapper.mapToDomain(boardModel))
        }
    }

    override suspend fun fetchAllLists(): List<ListModel> {
        return withContext(Dispatchers.IO) {
            databaseSource.getAllLists().map { entity ->
                listMapper.mapToData(entity)
            }
        }
    }

    override suspend fun fetchListsFromRelatedBoard(boardId: Int): List<ListModel> {
        return withContext(Dispatchers.IO) {
            databaseSource.getAllListsRelatedToBoard(boardId).map { entity ->
                listMapper.mapToData(entity)
            }
        }
    }
}