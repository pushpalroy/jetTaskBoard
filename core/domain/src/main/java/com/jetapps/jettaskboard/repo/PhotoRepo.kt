package com.jetapps.jettaskboard.repo

import com.jetapps.jettaskboard.model.ChangeBackgroundPhotoModel
import com.jetapps.jettaskboard.util.Result

interface PhotoRepo {
    suspend fun getRandomPhotoList(collectionId : Int) : Result<List<ChangeBackgroundPhotoModel>?>
    suspend fun getSearchResultPhotoList(query : String) : Result<List<ChangeBackgroundPhotoModel>?>
}