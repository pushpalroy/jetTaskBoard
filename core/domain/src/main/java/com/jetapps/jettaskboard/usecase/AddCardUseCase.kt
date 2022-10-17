package com.jetapps.jettaskboard.usecase

import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.repo.CardRepo
import javax.inject.Inject

class AddCardUseCase @Inject constructor(private val cardRepo: CardRepo) : UseCase<Unit, CardModel> {
    suspend operator fun invoke(cardModel: CardModel) = cardRepo.addCard(cardModel)
}
