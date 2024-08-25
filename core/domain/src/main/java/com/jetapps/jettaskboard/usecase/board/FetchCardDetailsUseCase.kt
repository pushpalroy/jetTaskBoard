package com.jetapps.jettaskboard.usecase.board

import com.jetapps.jettaskboard.repo.CardRepo
import javax.inject.Inject

class FetchCardDetailsUseCase @Inject constructor(
    private val cardRepo: CardRepo
) {
    suspend operator fun invoke(cardId: Long) = cardRepo.fetchCardDetails(cardId)
}