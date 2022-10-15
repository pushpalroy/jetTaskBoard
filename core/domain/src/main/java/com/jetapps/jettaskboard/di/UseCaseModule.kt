package com.jetapps.jettaskboard.di

import com.jetapps.jettaskboard.repo.CardRepo
import com.jetapps.jettaskboard.usecase.AddCardUseCase
import com.jetapps.jettaskboard.usecase.FetchCardsUseCase
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
}
