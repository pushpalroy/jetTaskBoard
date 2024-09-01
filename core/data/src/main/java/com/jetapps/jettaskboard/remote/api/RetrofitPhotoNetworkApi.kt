package com.jetapps.jettaskboard.remote.api

import com.jetapps.jettaskboard.model.random.RandomPhotoItemDataModel
import com.jetapps.jettaskboard.model.search.SearchPhotoItemDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

const val UNSPLASH_BASE_URL = "https://api.unsplash.com/"
// TODO : Update and hide the key
const val CLIENT_ID = "vJKXBk2ks9uUNHLhSRitTTZ8DIOg05ZofrqJCBkEgL8"

const val GET_RANDOM_PHOTO_LIST = "photos/random"
const val GET_SEARCH_PHOTO_LIST = "search/photos"
const val GET_COLOR_PALLET_LIST = "search/photos"

const val AUTHORIZATION_CLIENT_ID_PARAM = "client_id"
const val QUERY_PER_PAGE = "per_page"
const val QUERY_PARAM = "query"
const val QUERY_COUNT = "count"
const val QUERY_COLLECTION = "collection"

interface RetrofitPhotoNetworkApi {

    @GET(GET_RANDOM_PHOTO_LIST)
    suspend fun getRandomPhotoList(
        @Query(AUTHORIZATION_CLIENT_ID_PARAM) clientId: String? = CLIENT_ID,
        @Query(QUERY_COUNT) count: Int = 4,
        @Query(QUERY_COLLECTION) collection: Int,
    ): Response<List<RandomPhotoItemDataModel>>

    @GET(GET_SEARCH_PHOTO_LIST)
    suspend fun getSearchPhotoList(
        @Query(AUTHORIZATION_CLIENT_ID_PARAM) clientId: String? = CLIENT_ID,
        @Query(QUERY_PARAM) query: String?,
        @Query(QUERY_PER_PAGE) per_page: Int = 4,
    ): Response<SearchPhotoItemDataModel>
}