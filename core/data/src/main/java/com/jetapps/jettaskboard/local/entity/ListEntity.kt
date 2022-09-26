package com.jetapps.jettaskboard.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
  tableName = "listTable",
  foreignKeys = [
    ForeignKey(
      entity = BoardEntity::class,
      parentColumns = ["id"],
      childColumns = ["board_id"],
      onDelete = ForeignKey.CASCADE
    )
  ]
)
data class ListEntity(
  @PrimaryKey val id: String,
  val title: String,
  @ColumnInfo(name = "board_id")
  val boardId: String
)