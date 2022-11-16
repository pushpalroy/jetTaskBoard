package com.jetapps.jettaskboard.usecase.network

import com.jetapps.jettaskboard.model.ChangeBackgroundPhotoModel
import com.jetapps.jettaskboard.repo.PhotoRepo
import com.jetapps.jettaskboard.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

class GetRandomPhotoListUseCase @Inject constructor(
    private val photoRepo: PhotoRepo
) : UseCase<Flow<List<ChangeBackgroundPhotoModel>>, Unit> {
    suspend operator fun invoke() = photoRepo.getRandomPhotoList()
}