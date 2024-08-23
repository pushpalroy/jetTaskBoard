package com.jetapps.jettaskboard.usecase.dashboard

import com.jetapps.jettaskboard.model.BoardModel
import com.jetapps.jettaskboard.repo.DashboardRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchAllBoardsUseCase @Inject constructor(
    private val dashboardRepo: DashboardRepo
) {

    suspend operator fun invoke() : Flow<List<BoardModel>> {
        return dashboardRepo
            .fetchAllBoards()
    }
}