package com.jetapps.jettaskboard.usecase.board

import com.jetapps.jettaskboard.repo.BoardRepo
import javax.inject.Inject

class GetBoardDetailsUseCase @Inject constructor(
    private val boardRepo: BoardRepo
) {

    suspend operator fun invoke(id: Int) = boardRepo.getBoardDetails(id)
}