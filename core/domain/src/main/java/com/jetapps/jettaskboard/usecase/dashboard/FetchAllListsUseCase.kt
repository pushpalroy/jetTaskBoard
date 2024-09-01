package com.jetapps.jettaskboard.usecase.dashboard

import com.jetapps.jettaskboard.model.BoardModel
import com.jetapps.jettaskboard.model.ListModel
import com.jetapps.jettaskboard.repo.DashboardRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchAllListsUseCase @Inject constructor(
    private val dashboardRepo: DashboardRepo
) {

    suspend operator fun invoke() : List<ListModel> {
        return dashboardRepo.fetchAllLists()
    }
}