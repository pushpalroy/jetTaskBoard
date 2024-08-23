package com.jetapps.jettaskboard.usecase.dashboard

import com.jetapps.jettaskboard.model.BoardModel
import com.jetapps.jettaskboard.model.CardModel
import com.jetapps.jettaskboard.repo.DashboardRepo
import javax.inject.Inject

class CreateBoardUseCase @Inject constructor(
    private val dashboardRepo: DashboardRepo
) {
    suspend operator fun invoke(boardModel: BoardModel) {
        dashboardRepo.createBoard(boardModel)
    }
}