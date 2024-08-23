package com.jetapps.jettaskboard.di

import com.jetapps.jettaskboard.local.source.DatabaseSource
import com.jetapps.jettaskboard.local.source.DatabaseSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Singleton
    @Binds
    abstract fun bindsDataSourceModule(
        datasourceModuleImpl: DatabaseSourceImpl,
    ): DatabaseSource
}