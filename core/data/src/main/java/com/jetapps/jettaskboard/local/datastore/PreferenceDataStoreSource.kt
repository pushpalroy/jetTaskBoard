package com.jetapps.jettaskboard.local.datastore

import kotlinx.coroutines.flow.Flow

interface PreferenceDataStoreSource {
    suspend fun updateBackgroundImgUri(string: String)
    suspend fun getLatestBackgroundImgUri(): Flow<String?>
}