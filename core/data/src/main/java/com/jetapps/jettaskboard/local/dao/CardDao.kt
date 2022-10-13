package com.jetapps.jettaskboard.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jetapps.jettaskboard.local.entity.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Query("SELECT * FROM cardTable")
    fun getAllCards(): Flow<List<CardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCards(cards: List<CardEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCard(card: CardEntity)

    @Query("SELECT * FROM cardTable where board_id = :boardId")
    fun getAllCardsForBoard(boardId: String): Flow<List<CardEntity>>

    @Query("SELECT * FROM cardTable where board_id = :boardId and list_id = :listId")
    fun getAllCardsForBoardAndList(
        boardId: String,
        listId: String
    ): Flow<List<CardEntity>>
}
