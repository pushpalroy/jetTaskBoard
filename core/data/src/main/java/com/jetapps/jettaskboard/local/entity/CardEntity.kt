package com.jetapps.jettaskboard.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "cardTable"
)
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val cardId: Long = 0,
    val title: String,
    val description: String?,
    @ColumnInfo(name = "column_image_url")
    val coverImageUrl: String?,
    val boardId: Long,
    val listId: Long,
    @ColumnInfo(name = "author_id")
    val authorId: String?,
    @ColumnInfo(name = "start_date")
    val startDate: String?,
    @ColumnInfo(name = "due_date")
    val dueDate: String?
)
