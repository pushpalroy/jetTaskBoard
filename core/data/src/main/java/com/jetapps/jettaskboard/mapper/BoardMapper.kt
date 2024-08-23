package com.jetapps.jettaskboard.mapper

import com.jetapps.jettaskboard.local.entity.BoardEntity
import com.jetapps.jettaskboard.model.BoardModel
import javax.inject.Inject

class BoardMapper @Inject constructor() : EntityMapper<BoardEntity, BoardModel> {
    override fun mapToDomain(entity: BoardModel): BoardEntity {
        return BoardEntity(
            boardId = entity.id ?: 0,
            title = entity.title,
            description = entity.title,
            workSpaceId = entity.id ?: 0,
            isFav = 0
        )
    }

    override fun mapToData(model: BoardEntity): BoardModel {
        return BoardModel(
            id = model.boardId,
            title = model.title,
            isFav = model.isFav == 1,
        )
    }
}