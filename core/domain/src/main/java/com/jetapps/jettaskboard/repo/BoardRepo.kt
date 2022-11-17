package com.jetapps.jettaskboard.repo

import kotlinx.coroutines.flow.Flow

interface BoardRepo {
    suspend fun getLatestBackgroundImgUri(): Flow<String?>
    suspend fun updateBackgroundImgUri(string: String)
}