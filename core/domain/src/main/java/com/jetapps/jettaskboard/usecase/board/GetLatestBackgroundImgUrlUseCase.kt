package com.jetapps.jettaskboard.usecase.board

import com.jetapps.jettaskboard.repo.BoardRepo
import com.jetapps.jettaskboard.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLatestBackgroundImgUrlUseCase @Inject constructor(
    private val boardRepo: BoardRepo
) : UseCase<Flow<String?>, Unit> {

    suspend operator fun invoke() = boardRepo.getLatestBackgroundImgUri()
}