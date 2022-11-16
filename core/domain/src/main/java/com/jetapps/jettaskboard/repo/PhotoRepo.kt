package com.jetapps.jettaskboard.repo

import com.jetapps.jettaskboard.model.ChangeBackgroundPhotoModel

interface PhotoRepo {
    suspend fun getRandomPhotoList() : List<ChangeBackgroundPhotoModel>
}