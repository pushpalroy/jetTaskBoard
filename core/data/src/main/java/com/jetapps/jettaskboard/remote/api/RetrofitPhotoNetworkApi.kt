package com.jetapps.jettaskboard.remote.api

import com.jetapps.jettaskboard.model.RandomPhotoItemDataModel
import retrofit2.http.GET
import retrofit2.http.Query

const val UNSPLASH_BASE_URL = "https://api.unsplash.com/"
const val CLIENT_ID = "vJKXBk2ks9uUNHLhSRitTTZ8DIOg05ZofrqJCBkEgL8"
const val GET_RANDOM_PHOTO_LIST = "photos"
const val AUTHORIZATION_CLIENT_ID_PARAM = "client_id"

interface RetrofitPhotoNetworkApi {

    @GET(GET_RANDOM_PHOTO_LIST)
    suspend fun getRandomPhotoList(
        @Query(AUTHORIZATION_CLIENT_ID_PARAM) clientId: String? = CLIENT_ID
    ): List<RandomPhotoItemDataModel>
}