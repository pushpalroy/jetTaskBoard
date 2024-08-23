package com.jetapps.jettaskboard.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jetapps.jettaskboard.local.entity.LabelEntity

@Dao
interface LabelDao {

    @Query("SELECT * FROM labelTable")
    fun getAllLabels(): List<LabelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLabel(label: LabelEntity)

    @Delete
    fun deleteLabel(label: LabelEntity)
}
