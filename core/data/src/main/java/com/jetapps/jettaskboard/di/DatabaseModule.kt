package com.jetapps.jettaskboard.di

import android.content.Context
import androidx.room.Room
import com.jetapps.jettaskboard.local.dao.BoardDao
import com.jetapps.jettaskboard.local.dao.CardDao
import com.jetapps.jettaskboard.local.dao.LabelDao
import com.jetapps.jettaskboard.local.dao.ListDao
import com.jetapps.jettaskboard.local.database.JtbDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): JtbDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            JtbDatabase::class.java
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun providesBoardDao(database: JtbDatabase): BoardDao = database.boardDao()

    @Provides
    @Singleton
    fun providesCardDao(database: JtbDatabase): CardDao = database.cardDao()

    @Provides
    @Singleton
    fun providesLabelDao(database: JtbDatabase): LabelDao = database.labelDao()

    @Provides
    @Singleton
    fun providesListDao(database: JtbDatabase): ListDao = database.listDao()
}
