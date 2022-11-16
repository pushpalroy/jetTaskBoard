package com.jetapps.jettaskboard.repo

import com.jetapps.jettaskboard.dispatcher.CoroutineDispatcherProvider
import com.jetapps.jettaskboard.mapper.EntityMapper
import com.jetapps.jettaskboard.model.ChangeBackgroundPhotoModel
import com.jetapps.jettaskboard.model.RandomPhotoItemDataModel
import com.jetapps.jettaskboard.remote.data_source.PhotoNetworkDataSource
import kotlinx.coroutines.withContext

class PhotoRepoImpl(
    private val photoNetwork: PhotoNetworkDataSource,
    private val dispatcherProvider: CoroutineDispatcherProvider,
    private val entityMapper: EntityMapper<ChangeBackgroundPhotoModel, RandomPhotoItemDataModel>,
) : PhotoRepo {

    override suspend fun getRandomPhotoList(): List<ChangeBackgroundPhotoModel> {
        return withContext(dispatcherProvider.io) {
            photoNetwork.getRandomPhotoList()
        }.map { dataModel ->
            entityMapper.mapToDomain(dataModel)
        }
    }
}