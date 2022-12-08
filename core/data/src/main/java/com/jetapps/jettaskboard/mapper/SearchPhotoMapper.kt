package com.jetapps.jettaskboard.mapper

import com.jetapps.jettaskboard.model.ChangeBackgroundPhotoModel
import com.jetapps.jettaskboard.model.random.RandomPhotoItemDataModel
import com.jetapps.jettaskboard.model.search.ResultImageDataModel
import javax.inject.Inject

class SearchPhotoMapper @Inject constructor() :
    EntityMapper<ChangeBackgroundPhotoModel, ResultImageDataModel> {

    override fun mapToDomain(entity: ResultImageDataModel): ChangeBackgroundPhotoModel {
        return ChangeBackgroundPhotoModel(
            imageUrl = entity.urls?.regular,
            imageName = entity.user?.username,
        )
    }

    override fun mapToData(model: ChangeBackgroundPhotoModel): ResultImageDataModel {
        return ResultImageDataModel()
    }
}