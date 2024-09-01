package com.jetapps.jettaskboard.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "boardTable"
)
data class BoardEntity(
    @PrimaryKey(autoGenerate = false)
    val boardId: Long = 0,
    val title: String,
    val description: String,
    val isFav : Int,
    val workSpaceId : Int,
)
