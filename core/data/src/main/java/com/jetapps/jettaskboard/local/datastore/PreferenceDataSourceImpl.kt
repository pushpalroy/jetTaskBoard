package com.jetapps.jettaskboard.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferenceDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PreferenceDataStoreSource {

    val LATEST_BG_IMG_URI_STRING_KEY = stringPreferencesKey(name = "latest_bg_img_uri")

    override suspend fun updateBackgroundImgUri(string: String) {
        dataStore.edit { preferences ->
            preferences[LATEST_BG_IMG_URI_STRING_KEY] = string
        }
    }

    override suspend fun getLatestBackgroundImgUri(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[LATEST_BG_IMG_URI_STRING_KEY] ?: ""
        }
    }
}