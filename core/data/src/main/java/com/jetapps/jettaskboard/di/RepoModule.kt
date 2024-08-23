package com.jetapps.jettaskboard.di

import com.jetapps.jettaskboard.dispatcher.CoroutineDispatcherProvider
import com.jetapps.jettaskboard.local.dao.CardDao
import com.jetapps.jettaskboard.local.datastore.PreferenceDataStoreSource
import com.jetapps.jettaskboard.local.entity.CardEntity
import com.jetapps.jettaskboard.local.source.DatabaseSource
import com.jetapps.jettaskboard.mapper.BoardMapper
import com.jetapps.jettaskboard.mapper.CardMapper
import com.jetapps.jettaskboard.mapper.EntityMapper
import com.jetapps.jettaskboard.mapper.ListMapper
import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.model.ChangeBackgroundPhotoModel
import com.jetapps.jettaskboard.model.random.RandomPhotoItemDataModel
import com.jetapps.jettaskboard.model.search.ResultImageDataModel
import com.jetapps.jettaskboard.remote.data_source.PhotoNetworkDataSource
import com.jetapps.jettaskboard.repo.BoardRepo
import com.jetapps.jettaskboard.repo.BoardRepoImpl
import com.jetapps.jettaskboard.repo.CardRepo
import com.jetapps.jettaskboard.repo.CardRepoImpl
import com.jetapps.jettaskboard.repo.DashboardRepo
import com.jetapps.jettaskboard.repo.DashboardRepoImpl
import com.jetapps.jettaskboard.repo.PhotoRepo
import com.jetapps.jettaskboard.repo.PhotoRepoImpl
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

    @Provides
    @Singleton
    fun providePhotoRepo(
        photoNetworkResource: PhotoNetworkDataSource,
        randomPhotoMapper: EntityMapper<ChangeBackgroundPhotoModel, RandomPhotoItemDataModel>,
        searchPhotoMapper: EntityMapper<ChangeBackgroundPhotoModel, ResultImageDataModel>,
        dispatcherProvider: CoroutineDispatcherProvider
    ): PhotoRepo = PhotoRepoImpl(
        photoNetwork = photoNetworkResource,
        dispatcherProvider = dispatcherProvider,
        randomPhotoMapper = randomPhotoMapper,
        searchPhotoMapper = searchPhotoMapper
    )

    @Provides
    @Singleton
    fun provideBoardRepo(
        preferenceDataStoreSource: PreferenceDataStoreSource,
        databaseSource: DatabaseSource,
        boardMapper: BoardMapper,
        listMapper: ListMapper,
        cardMapper: CardMapper
    ): BoardRepo = BoardRepoImpl(
        preferenceDataStoreSource = preferenceDataStoreSource,
        databaseSource = databaseSource,
        boardMapper = boardMapper,
        listMapper = listMapper,
        cardMapper = cardMapper,
    )

    @Provides
    @Singleton
    fun provideDashboardRepo(
        databaseSource: DatabaseSource,
        boardMapper: BoardMapper,
        cardMapper: CardMapper,
        listMapper: ListMapper,
    ): DashboardRepo {
        return DashboardRepoImpl(
            databaseSource = databaseSource,
            boardMapper = boardMapper,
            cardMapper = cardMapper,
            listMapper = listMapper
        )
    }
}
