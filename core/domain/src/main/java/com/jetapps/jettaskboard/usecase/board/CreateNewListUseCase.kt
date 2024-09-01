package com.jetapps.jettaskboard.usecase.board

import com.jetapps.jettaskboard.model.ListModel
import com.jetapps.jettaskboard.repo.BoardRepo
import javax.inject.Inject

class CreateNewListUseCase @Inject constructor(
    private val boardRepo: BoardRepo
) {
    suspend operator fun invoke(listModel: ListModel) =
        boardRepo.createNewList(listModel)
}