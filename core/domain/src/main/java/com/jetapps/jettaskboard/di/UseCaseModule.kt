package com.jetapps.jettaskboard.di

import com.jetapps.jettaskboard.repo.BoardRepo
import com.jetapps.jettaskboard.repo.CardRepo
import com.jetapps.jettaskboard.repo.PhotoRepo
import com.jetapps.jettaskboard.usecase.AddCardUseCase
import com.jetapps.jettaskboard.usecase.FetchCardsUseCase
import com.jetapps.jettaskboard.usecase.board.GetLatestBackgroundImgUrlUseCase
import com.jetapps.jettaskboard.usecase.board.UpdateTaskBoardBackgroundImgUriUseCase
import com.jetapps.jettaskboard.usecase.network.GetRandomPhotoListUseCase
import com.jetapps.jettaskboard.usecase.network.SearchQueryForImageListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideFetchCardsUseCase(cardRepo: CardRepo): FetchCardsUseCase =
        FetchCardsUseCase(cardRepo)

    @Provides
    @Singleton
    fun provideAddCardUseCase(cardRepo: CardRepo): AddCardUseCase =
        AddCardUseCase(cardRepo)

    @Provides
    @Singleton
    fun provideGetRandomPhotoListUseCase(photoRepo: PhotoRepo): GetRandomPhotoListUseCase =
        GetRandomPhotoListUseCase(photoRepo)

    @Provides
    @Singleton
    fun provideGetSearchResultPhotoListUseCase(photoRepo: PhotoRepo): SearchQueryForImageListUseCase =
        SearchQueryForImageListUseCase(photoRepo)

    @Provides
    @Singleton
    fun provideGetLatestBackgroundImgUrlUseCase(boardRepo: BoardRepo): GetLatestBackgroundImgUrlUseCase =
        GetLatestBackgroundImgUrlUseCase(boardRepo)

    @Provides
    @Singleton
    fun provideUpdateLatestBackgroundImgUriUseCase(boardRepo: BoardRepo): UpdateTaskBoardBackgroundImgUriUseCase =
        UpdateTaskBoardBackgroundImgUriUseCase(boardRepo)

}
