package com.jetapps.jettaskboard.repo

import com.jetapps.jettaskboard.local.datastore.PreferenceDataStoreSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BoardRepoImpl @Inject constructor(
    private val preferenceDataStoreSource: PreferenceDataStoreSource
) : BoardRepo {
    override suspend fun getLatestBackgroundImgUri(): Flow<String?> {
        return preferenceDataStoreSource.getLatestBackgroundImgUri()
    }

    override suspend fun updateBackgroundImgUri(string: String) {
        preferenceDataStoreSource.updateBackgroundImgUri(string)
    }
}