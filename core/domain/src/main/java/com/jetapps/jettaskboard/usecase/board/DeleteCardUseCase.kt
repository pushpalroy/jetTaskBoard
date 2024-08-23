package com.jetapps.jettaskboard.usecase.board

import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.repo.BoardRepo
import javax.inject.Inject

class DeleteCardUseCase @Inject constructor(
    private val boardRepo: BoardRepo
) {
    suspend operator fun invoke(cardModel: CardModel) =
        boardRepo.deleteCard(cardModel)
}