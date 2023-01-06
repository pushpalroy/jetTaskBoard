package com.jetapps.jettaskboard.di

import com.jetapps.jettaskboard.local.entity.CardEntity
import com.jetapps.jettaskboard.mapper.CardMapper
import com.jetapps.jettaskboard.mapper.EntityMapper
import com.jetapps.jettaskboard.mapper.RandomPhotoMapper
import com.jetapps.jettaskboard.mapper.SearchPhotoMapper
import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.model.ChangeBackgroundPhotoModel
import com.jetapps.jettaskboard.model.random.RandomPhotoItemDataModel
import com.jetapps.jettaskboard.model.search.ResultImageDataModel
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

    @Binds
    @Singleton
    abstract fun bindBackgroundPhotoMapper(
        randomPhotoMapper: RandomPhotoMapper
    ): EntityMapper<ChangeBackgroundPhotoModel, RandomPhotoItemDataModel>

    @Binds
    @Singleton
    abstract fun bindSearchPhotoMapper(
        searchPhotoMapper: SearchPhotoMapper
    ): EntityMapper<ChangeBackgroundPhotoModel, ResultImageDataModel>
}
