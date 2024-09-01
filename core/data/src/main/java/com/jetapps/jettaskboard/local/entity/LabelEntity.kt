package com.jetapps.jettaskboard.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "labelTable"
)
data class LabelEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "label_name")
    val labelName: String,
    @ColumnInfo(name = "label_color")
    val labelColor: String
)
