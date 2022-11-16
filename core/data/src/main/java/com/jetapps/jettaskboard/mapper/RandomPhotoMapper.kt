package com.jetapps.jettaskboard.mapper

import com.jetapps.jettaskboard.model.ChangeBackgroundPhotoModel
import com.jetapps.jettaskboard.model.RandomPhotoItemDataModel
import javax.inject.Inject

class RandomPhotoMapper @Inject constructor() : EntityMapper<ChangeBackgroundPhotoModel, RandomPhotoItemDataModel> {

    override fun mapToDomain(entity: RandomPhotoItemDataModel): ChangeBackgroundPhotoModel {
        return ChangeBackgroundPhotoModel(
            imageUrl = entity.urlDataModel?.regular,
            imageName = entity.userDataModel?.username,
        )
    }

    override fun mapToData(model: ChangeBackgroundPhotoModel): RandomPhotoItemDataModel {
        return RandomPhotoItemDataModel()
    }
}