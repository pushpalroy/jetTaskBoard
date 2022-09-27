package com.jetapps.jettaskboard.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
  tableName = "cardTable",
  // foreignKeys = [
  //   ForeignKey(
  //     entity = BoardEntity::class,
  //     parentColumns = ["id"],
  //     childColumns = ["board_id"],
  //     onDelete = ForeignKey.CASCADE
  //   ),
  //   ForeignKey(
  //     entity = ListEntity::class,
  //     parentColumns = ["id"],
  //     childColumns = ["list_id"],
  //     onDelete = ForeignKey.CASCADE
  //   )
  // ]
)
data class CardEntity(
  @PrimaryKey(autoGenerate = true) val id: Int,
  val title: String,
  val description: String?,
  @ColumnInfo(name = "column_image_url")
  val coverImageUrl: String?,
  @ColumnInfo(name = "board_id")
  val boardId: String?,
  @ColumnInfo(name = "list_id")
  val listId: Int?,
  @ColumnInfo(name = "author_id")
  val authorId: String?,
  @ColumnInfo(name = "start_date")
  val startDate: String?,
  @ColumnInfo(name = "due_date")
  val dueDate: String?
)