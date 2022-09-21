package com.jetapps.jettaskboard.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jetapps.jettaskboard.local.dao.BoardDao
import com.jetapps.jettaskboard.local.dao.CardDao
import com.jetapps.jettaskboard.local.dao.LabelDao
import com.jetapps.jettaskboard.local.dao.ListDao
import com.jetapps.jettaskboard.local.entity.AttachmentEntity
import com.jetapps.jettaskboard.local.entity.BoardEntity
import com.jetapps.jettaskboard.local.entity.CardEntity
import com.jetapps.jettaskboard.local.entity.LabelEntity
import com.jetapps.jettaskboard.local.entity.ListEntity
import com.jetapps.jettaskboard.local.entity.MemberEntity

@Database(
  entities = [
    CardEntity::class,
    ListEntity::class,
    BoardEntity::class,
    LabelEntity::class,
    MemberEntity::class,
    AttachmentEntity::class
  ],
  version = 1,
  exportSchema = false
)
abstract class JtbDatabase : RoomDatabase() {

  abstract fun boardDao(): BoardDao
  abstract fun listDao(): ListDao
  abstract fun cardDao(): CardDao
  abstract fun labelDao(): LabelDao
}