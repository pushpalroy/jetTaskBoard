package com.jetapps.jettaskboard.remote.api

import com.jetapps.jettaskboard.model.RandomPhotoItemDataModel
import com.jetapps.jettaskboard.remote.data_source.PhotoNetworkDataSource
import okhttp3.MediaType.Companion.toMediaType
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

    override suspend fun getRandomPhotoList(): List<RandomPhotoItemDataModel> {
        return unsplashPhotoApi.getRandomPhotoList()
    }
}