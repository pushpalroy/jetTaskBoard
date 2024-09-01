package com.jetapps.jettaskboard.usecase.dashboard

import com.jetapps.jettaskboard.model.ListModel
import com.jetapps.jettaskboard.repo.DashboardRepo
import javax.inject.Inject

class FetchAllListsRelatedToBoardUseCase @Inject constructor(
    private val dashboardRepo: DashboardRepo
) {

    suspend operator fun invoke(id : Long): List<ListModel> {
        return dashboardRepo.fetchListsFromRelatedBoard(id)
    }
}