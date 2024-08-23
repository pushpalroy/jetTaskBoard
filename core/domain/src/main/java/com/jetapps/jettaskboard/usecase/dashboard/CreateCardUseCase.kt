package com.jetapps.jettaskboard.usecase.dashboard

import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.repo.DashboardRepo
import javax.inject.Inject

class CreateCardUseCase @Inject constructor(
    private val dashboardRepo: DashboardRepo
) {

    suspend operator fun invoke(cardModel: CardModel) {
        dashboardRepo.createCard(cardModel)
    }
}