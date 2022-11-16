package com.jetapps.jettaskboard.remote.data_source

import com.jetapps.jettaskboard.model.RandomPhotoItemDataModel

interface PhotoNetworkDataSource {
    suspend fun getRandomPhotoList(): List<RandomPhotoItemDataModel>
}