package com.jetapps.jettaskboard.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "attachmentTable"
)
data class AttachmentEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "attachment_name")
    val attachmentName: String,
    @ColumnInfo(name = "attachment_url")
    val attachmentUrl: String,
    @ColumnInfo(name = "attachment_type")
    val attachmentType: String
)
