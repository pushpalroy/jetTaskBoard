package com.jetapps.jettaskboard.usecase.dashboard

import com.jetapps.jettaskboard.model.ProfileModel
import com.jetapps.jettaskboard.repo.DashboardRepo
import javax.inject.Inject

class FetchProfileUseCase @Inject constructor(
    private val dashboardRepo: DashboardRepo
) {
    suspend operator fun invoke(): ProfileModel {
        return dashboardRepo.fetchProfile()
    }
}