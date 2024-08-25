package com.jetapps.jettaskboard.usecase

import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.repo.CardRepo
import javax.inject.Inject

class FetchCardsUseCase @Inject constructor(
    private val cardRepo: CardRepo,
) : UseCase<List<CardModel>, String> {
    suspend operator fun invoke(boardId: String) = cardRepo.fetchCards(boardId = boardId)
}
