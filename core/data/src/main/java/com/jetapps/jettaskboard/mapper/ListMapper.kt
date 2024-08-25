package com.jetapps.jettaskboard.mapper

import com.jetapps.jettaskboard.local.entity.ListEntity
import com.jetapps.jettaskboard.model.ListModel
import javax.inject.Inject

class ListMapper @Inject constructor(
    private val cardMapper: CardMapper,
) : EntityMapper<ListEntity, ListModel> {
    override fun mapToDomain(entity: ListModel): ListEntity {
        return ListEntity(
            title = entity.title ?: "",
            boardId = entity.boardId ?: 0,
        )
    }

    override fun mapToData(model: ListEntity): ListModel {
        return ListModel(
            listId = model.listId,
            title = model.title,
            boardId = model.boardId
        )
    }
}