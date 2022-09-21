package com.jetapps.jettaskboard.mapper

import com.jetapps.jettaskboard.local.entity.CardEntity
import com.jetapps.jettaskboard.model.CardModel
import javax.inject.Inject

class CardMapper @Inject constructor() : EntityMapper<CardModel, CardEntity> {

  override fun mapToDomain(entity: CardEntity): CardModel {
    return CardModel(
      entity.id,
      entity.title,
      entity.description,
      entity.coverImageUrl,
      entity.boardId,
      entity.listId,
      entity.authorId,
      entity.startDate,
      entity.dueDate
    )
  }

  override fun mapToData(model: CardModel): CardEntity {
    return CardEntity(
      model.id,
      model.title,
      model.description,
      model.coverImageUrl,
      model.boardId,
      model.listId,
      model.authorId,
      model.startDate,
      model.dueDate
    )
  }
}