package com.jetapps.jettaskboard.di

import com.jetapps.jettaskboard.local.datastore.PreferenceDataSourceImpl
import com.jetapps.jettaskboard.local.datastore.PreferenceDataStoreSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PreferenceDataSourceModule {

    @Binds
    fun providePreferenceDataSource(
        preferenceDataSourceImpl: PreferenceDataSourceImpl
    ): PreferenceDataStoreSource
}