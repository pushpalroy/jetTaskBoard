package com.jetapps.jettaskboard.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "listTable",
)
data class ListEntity(
    @PrimaryKey(autoGenerate = true)
    val listId: Int = 0,
    val title: String,
    val boardId: Int
)
