package com.jetapps.jettaskboard.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jetapps.jettaskboard.local.entity.ListEntity

@Dao
interface ListDao {

    @Query("SELECT * FROM listTable")
    fun getAllLists(): List<ListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: ListEntity)

    @Delete
    fun deleteList(list: ListEntity)

    @Query("SELECT * FROM listTable where boardId = :boardId")
    fun getAllListsForBoard(boardId: String): List<ListEntity>
}
