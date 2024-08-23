package com.jetapps.jettaskboard.local.source

import com.jetapps.jettaskboard.local.dao.CardDao
import com.jetapps.jettaskboard.local.dao.DashboardDao
import com.jetapps.jettaskboard.local.dao.LabelDao
import com.jetapps.jettaskboard.local.dao.ListDao
import com.jetapps.jettaskboard.local.entity.BoardEntity
import com.jetapps.jettaskboard.local.entity.CardEntity
import com.jetapps.jettaskboard.local.entity.LabelEntity
import com.jetapps.jettaskboard.local.entity.ListEntity
import com.jetapps.jettaskboard.model.BoardModel
import com.jetapps.jettaskboard.model.db.BoardWithLists
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DatabaseSourceImpl @Inject constructor(
    private val dashboardDao: DashboardDao,
    private val cardDao: CardDao,
    private val listDao: ListDao,
    private val labelDao: LabelDao
) : DatabaseSource {

    override suspend fun createBoard(boardEntity: BoardEntity) {
        dashboardDao.insertBoard(boardEntity)
    }

    override suspend fun getBoard(boardId: Int): Flow<BoardWithLists> {
        return dashboardDao.getBoardDetails(boardId)
    }

    override suspend fun getBoards(): Flow<List<BoardEntity>> {
        return dashboardDao.getAllBoards()
    }

    override suspend fun getAllLists(): List<ListEntity> {
        return dashboardDao.getAllLists()
    }

    override suspend fun getAllListsRelatedToBoard(boardId: Int): List<ListEntity> {
        return dashboardDao.getAllBoardRelatedLists(boardId)
    }

    override suspend fun updateCard(cardEntity: CardEntity) {
        cardDao.updateCard(cardEntity)
    }

    override suspend fun createCard(cardEntity: CardEntity) {
        withContext(Dispatchers.IO) {
            cardDao.insertCard(cardEntity)
        }
    }

    override suspend fun deleteCard(cardEntity: CardEntity) {
        cardDao.deleteCard(cardEntity)
    }

    override suspend fun createNewList(listEntity: ListEntity) {
        withContext(Dispatchers.IO) {
            listDao.insertList(listEntity)
        }
    }

    override suspend fun deleteList(listEntity: ListEntity) {
        listDao.deleteList(listEntity)
    }

    override suspend fun createLabel(labelEntity: LabelEntity) {
        labelDao.insertLabel(labelEntity)
    }

    override suspend fun deleteLabel(labelEntity: LabelEntity) {
        labelDao.deleteLabel(labelEntity)
    }


}