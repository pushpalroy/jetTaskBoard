package com.jetapps.jettaskboard.model.search

import com.google.gson.annotations.SerializedName

data class SearchPhotoItemDataModel(
    @SerializedName("total")
    val total: Int? = 0,
    @SerializedName("total_pages")
    val totalPages: Int? = 0,
    @SerializedName("results")
    val resultImageDataModels: List<ResultImageDataModel>? = listOf()
)