package com.jetapps.jettaskboard.mapper

import com.jetapps.jettaskboard.local.entity.CardEntity
import com.jetapps.jettaskboard.model.CardModel
import javax.inject.Inject

class CardMapper @Inject constructor(
    private val labelMapper: LabelMapper,
) : EntityMapper<CardModel, CardEntity> {

    override fun mapToDomain(entity: CardEntity): CardModel {
        return CardModel(
            entity.cardId,
            entity.title,
            entity.description,
            entity.coverImageUrl,
            listOf(),
            entity.boardId,
            entity.listId,
            entity.authorId,
            entity.startDate,
            entity.dueDate
        )
    }

    override fun mapToData(model: CardModel): CardEntity {
        return CardEntity(
            title = model.title,
            description = model.description,
            coverImageUrl = model.coverImageUrl,
            boardId = model.boardId,
            listId = model.listId,
            authorId = model.authorId,
            startDate = model.startDate,
            dueDate = model.dueDate
        )
    }
}

/**
 * When creating a new Card, we don't want to pass the Id,
 * But for updating the card we need Id
 * Creating a extension function for updating the card and
 * Mapper Class for new card creation.
 */
fun CardModel.mapToDomain(): CardEntity {
    return CardEntity(
        cardId = id,
        title = title,
        description = description,
        coverImageUrl = coverImageUrl,
        boardId = boardId,
        listId = listId,
        authorId = authorId,
        startDate = startDate,
        dueDate = dueDate
    )
}

