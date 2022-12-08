package com.jetapps.jettaskboard.remote.data_source

import com.jetapps.jettaskboard.model.random.RandomPhotoItemDataModel
import com.jetapps.jettaskboard.model.search.SearchPhotoItemDataModel
import com.jetapps.jettaskboard.util.NetworkResponse

interface PhotoNetworkDataSource {
    suspend fun getRandomPhotoList(collectionId : Int): NetworkResponse<List<RandomPhotoItemDataModel>>
    suspend fun getSearchPhotoList(query : String): NetworkResponse<SearchPhotoItemDataModel>
}