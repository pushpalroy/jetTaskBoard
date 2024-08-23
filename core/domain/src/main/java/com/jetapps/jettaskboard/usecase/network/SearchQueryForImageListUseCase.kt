package com.jetapps.jettaskboard.usecase.network

import com.jetapps.jettaskboard.model.ChangeBackgroundPhotoModel
import com.jetapps.jettaskboard.repo.PhotoRepo
import com.jetapps.jettaskboard.usecase.UseCase
import javax.inject.Inject

class SearchQueryForImageListUseCase @Inject constructor(
    private val iPhotoRepo: PhotoRepo
) : UseCase<Result<List<ChangeBackgroundPhotoModel>>, String> {

    suspend operator fun invoke(query: String) = iPhotoRepo.getSearchResultPhotoList(query)
}