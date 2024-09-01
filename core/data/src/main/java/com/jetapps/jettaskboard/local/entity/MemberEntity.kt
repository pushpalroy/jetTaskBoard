package com.jetapps.jettaskboard.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "memberTable"
)
data class MemberEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "member_name")
    val memberName: String,
    @ColumnInfo(name = "member_photo_url")
    val memberPhotoUrl: String
)
