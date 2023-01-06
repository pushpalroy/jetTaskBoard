package com.jetapps.jettaskboard.di

import com.jetapps.jettaskboard.remote.api.RetrofitPhotoNetwork
import com.jetapps.jettaskboard.remote.data_source.PhotoNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindPhotoNetworkSource(
        retrofitPhotoNetwork: RetrofitPhotoNetwork
    ): PhotoNetworkDataSource
}