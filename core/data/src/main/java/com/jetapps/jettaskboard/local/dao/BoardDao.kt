package com.jetapps.jettaskboard.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.jetapps.jettaskboard.local.entity.CardEntity
import com.jetapps.jettaskboard.local.entity.ListEntity
import com.jetapps.jettaskboard.model.db.BoardWithLists

@Dao
interface BoardDao {

    @Transaction
    @Query("SELECT * FROM boardTable WHERE boardId = :boardId")
    fun getBoardWithListsAndCards(boardId: Int): BoardWithLists
}