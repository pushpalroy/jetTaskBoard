package com.jetapps.jettaskboard.usecase.board

import com.jetapps.jettaskboard.model.BoardWithListAndCard
import com.jetapps.jettaskboard.repo.BoardRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetBoardDetailsUseCase @Inject constructor(
    private val boardRepo: BoardRepo
) {

    suspend operator fun invoke(id: Long) = boardRepo.getBoardDetails(id)
}