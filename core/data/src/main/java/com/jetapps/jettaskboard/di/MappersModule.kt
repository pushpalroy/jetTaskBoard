package com.jetapps.jettaskboard.di

import com.jetapps.jettaskboard.local.entity.CardEntity
import com.jetapps.jettaskboard.mapper.CardMapper
import com.jetapps.jettaskboard.mapper.EntityMapper
import com.jetapps.jettaskboard.model.CardModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MappersModule {

  @Binds
  @Singleton
  abstract fun bindCardMapper(cardMapper: CardMapper): EntityMapper<CardModel, CardEntity>
}