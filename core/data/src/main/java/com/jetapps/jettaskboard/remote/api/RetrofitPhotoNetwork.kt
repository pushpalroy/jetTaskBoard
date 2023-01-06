package com.jetapps.jettaskboard.remote.api

import com.jetapps.jettaskboard.model.random.RandomPhotoItemDataModel
import com.jetapps.jettaskboard.model.search.SearchPhotoItemDataModel
import com.jetapps.jettaskboard.remote.data_source.PhotoNetworkDataSource
import com.jetapps.jettaskboard.util.NetworkResponse
import com.jetapps.jettaskboard.util.safeApiCallResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitPhotoNetwork @Inject constructor() : PhotoNetworkDataSource {

    // Todo Inject API, provide it from outside.
    private val unsplashPhotoApi = Retrofit.Builder()
        .baseUrl(UNSPLASH_BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                ).build()
        )
        .addConverterFactory(
            // Todo Use Kotlin Serialization Kotlin `Json`
            GsonConverterFactory.create()
        )
        .build()
        .create(RetrofitPhotoNetworkApi::class.java)

    override suspend fun getRandomPhotoList(collectionId: Int): NetworkResponse<List<RandomPhotoItemDataModel>> {
        return safeApiCallResponse {
            unsplashPhotoApi.getRandomPhotoList(
                collection = collectionId
            )
        }
    }

    override suspend fun getSearchPhotoList(query: String): NetworkResponse<SearchPhotoItemDataModel> {
        return safeApiCallResponse {
            unsplashPhotoApi.getSearchPhotoList(
                query = query
            )
        }
    }
}