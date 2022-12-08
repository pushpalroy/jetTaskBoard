package com.jetapps.jettaskboard.repo

import com.jetapps.jettaskboard.dispatcher.CoroutineDispatcherProvider
import com.jetapps.jettaskboard.mapper.EntityMapper
import com.jetapps.jettaskboard.model.ChangeBackgroundPhotoModel
import com.jetapps.jettaskboard.model.random.RandomPhotoItemDataModel
import com.jetapps.jettaskboard.model.search.ResultImageDataModel
import com.jetapps.jettaskboard.remote.data_source.PhotoNetworkDataSource
import com.jetapps.jettaskboard.util.NetworkResponse
import com.jetapps.jettaskboard.util.Result
import kotlinx.coroutines.withContext

class PhotoRepoImpl(
    private val photoNetwork: PhotoNetworkDataSource,
    private val dispatcherProvider: CoroutineDispatcherProvider,
    private val randomPhotoMapper: EntityMapper<ChangeBackgroundPhotoModel, RandomPhotoItemDataModel>,
    private val searchPhotoMapper: EntityMapper<ChangeBackgroundPhotoModel, ResultImageDataModel>,
) : PhotoRepo {

    override suspend fun getRandomPhotoList(collectionId : Int): Result<List<ChangeBackgroundPhotoModel>?> {
        return withContext(dispatcherProvider.io) {
            when (val result = photoNetwork.getRandomPhotoList(collectionId)) {
                is NetworkResponse.Success -> Result.Success(
                    data = result.data.map { responseModel ->
                        randomPhotoMapper.mapToDomain(responseModel)
                    })
                is NetworkResponse.Failure -> Result.Failure(result.throwable.toString())
                is NetworkResponse.Unauthorized -> Result.Failure("No Connections")
            }
        }
    }

    override suspend fun getSearchResultPhotoList(query: String): Result<List<ChangeBackgroundPhotoModel>?> {
        return withContext(dispatcherProvider.io) {
            when (val result = photoNetwork.getSearchPhotoList(query = query)) {
                is NetworkResponse.Success -> Result.Success(
                    data = result.data.resultImageDataModels?.map { responseModel ->
                        searchPhotoMapper.mapToDomain(responseModel)
                    })
                is NetworkResponse.Failure -> Result.Failure(result.throwable.toString())
                is NetworkResponse.Unauthorized -> Result.Failure("No Connections")
            }
        }
    }
}