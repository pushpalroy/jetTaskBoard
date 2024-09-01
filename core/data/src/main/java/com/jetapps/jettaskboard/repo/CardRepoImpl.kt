package com.jetapps.jettaskboard.repo

import com.jetapps.jettaskboard.dispatcher.CoroutineDispatcherProvider
import com.jetapps.jettaskboard.local.dao.CardDao
import com.jetapps.jettaskboard.local.entity.CardEntity
import com.jetapps.jettaskboard.mapper.EntityMapper
import com.jetapps.jettaskboard.model.CardModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardRepoImpl @Inject constructor(
    private val cardDao: CardDao,
    private val entityMapper: EntityMapper<CardModel, CardEntity>,
    private val dispatcherProvider: CoroutineDispatcherProvider
) : CardRepo {

    override suspend fun addCard(cardModel: CardModel) {
        withContext(dispatcherProvider.io) {
            cardDao.insertCard(entityMapper.mapToData(cardModel))
        }
    }

    override suspend fun fetchCards(boardId: String): Flow<List<CardModel>> {
        return withContext(dispatcherProvider.io) {
            cardDao.getAllCardsForBoard(boardId)
        }.mapLatest { cardEntityList ->
            cardEntityList.map { entityMapper.mapToDomain(it) }
        }
    }

    override suspend fun fetchCardDetails(cardId: Long): CardModel {
        return withContext(dispatcherProvider.io) {
            entityMapper.mapToDomain(cardDao.fetchCardDetails(cardId))
        }
    }
}
