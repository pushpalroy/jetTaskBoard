package com.jetapps.jettaskboard.repo

import com.jetapps.jettaskboard.model.CardModel
import kotlinx.coroutines.flow.Flow

interface CardRepo {

    suspend fun addCard(cardModel: CardModel)

    suspend fun fetchCards(
        boardId: String
    ): Flow<List<CardModel>>

    suspend fun fetchCardDetails(
        cardId : Long
    ) : CardModel
}
