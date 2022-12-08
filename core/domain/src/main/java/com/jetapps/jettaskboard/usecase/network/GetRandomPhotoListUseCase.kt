package com.jetapps.jettaskboard.usecase.network

import com.jetapps.jettaskboard.model.ChangeBackgroundPhotoModel
import com.jetapps.jettaskboard.repo.PhotoRepo
import com.jetapps.jettaskboard.usecase.UseCase
import com.jetapps.jettaskboard.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject

class GetRandomPhotoListUseCase @Inject constructor(
    private val photoRepo: PhotoRepo
) : UseCase<Result<List<ChangeBackgroundPhotoModel>>, Int> {
    suspend operator fun invoke(collectionId: Int) =
        when (val result = photoRepo.getRandomPhotoList(collectionId)) {
            is Result.Failure -> Result.Failure(message = result.message)
            is Result.Success -> Result.Success(result.data)
        }
}