package com.jetapps.jettaskboard.model.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.jetapps.jettaskboard.local.entity.BoardEntity
import com.jetapps.jettaskboard.local.entity.CardEntity
import com.jetapps.jettaskboard.local.entity.LabelEntity
import com.jetapps.jettaskboard.local.entity.ListEntity

@Entity(primaryKeys = ["listId", "cardId"])
data class ListWithCardCrossRef(
    val listId: Int,
    val cardId: Int,
)

/**
 * A board can have multiple Lists
 * thus, it follows the `one-to-many` relationship
 * In a relation with same `ids` a board can have n lists
 * but many list can't have n boards
 */
data class BoardWithLists(
    @Embedded val boardEntity: BoardEntity,
    @Relation(
        entity = ListEntity::class,
        parentColumn = "boardId",
        entityColumn = "boardId",
    )
    val boardList: List<ListWithCards> = emptyList()
)

/**
 * A list can have multiple Cards
 * thus, it also follows the `one-to-many` relationship
 * In a relation with same `ids` a list can have n cards
 * but many cards can't have n lists.
 */
data class ListWithCards(
    @Embedded val columnList: ListEntity,
    @Relation(
        parentColumn = "listId",
        entityColumn = "listId",
        entity = CardEntity::class
    )
    val cardList: List<CardEntity> = emptyList()
)