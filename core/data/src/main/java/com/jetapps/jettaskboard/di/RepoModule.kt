package com.jetapps.jettaskboard.di

import com.jetapps.jettaskboard.dispatcher.CoroutineDispatcherProvider
import com.jetapps.jettaskboard.local.dao.CardDao
import com.jetapps.jettaskboard.local.entity.CardEntity
import com.jetapps.jettaskboard.mapper.EntityMapper
import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.repo.CardRepo
import com.jetapps.jettaskboard.repo.CardRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideCardRepo(
        cardDao: CardDao,
        entityMapper: EntityMapper<CardModel, CardEntity>,
        dispatcherProvider: CoroutineDispatcherProvider
    ): CardRepo = CardRepoImpl(
        cardDao,
        entityMapper,
        dispatcherProvider
    )
}
