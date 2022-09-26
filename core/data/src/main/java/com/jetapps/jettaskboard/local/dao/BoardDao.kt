package com.jetapps.jettaskboard.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jetapps.jettaskboard.local.entity.BoardEntity

@Dao
interface BoardDao {

  @Query("SELECT * FROM boardTable")
  fun getAllBoards(): List<BoardEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertBoard(board: BoardEntity)
}