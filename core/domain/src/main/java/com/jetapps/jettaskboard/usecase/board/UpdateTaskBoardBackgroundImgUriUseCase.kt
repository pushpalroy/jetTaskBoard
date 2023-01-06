package com.jetapps.jettaskboard.usecase.board

import com.jetapps.jettaskboard.repo.BoardRepo
import com.jetapps.jettaskboard.usecase.UseCase
import javax.inject.Inject

class UpdateTaskBoardBackgroundImgUriUseCase @Inject constructor(
    private val boardRepo: BoardRepo
) : UseCase<Unit, String> {

    suspend operator fun invoke(imageUri: String) = boardRepo.updateBackgroundImgUri(imageUri)
}