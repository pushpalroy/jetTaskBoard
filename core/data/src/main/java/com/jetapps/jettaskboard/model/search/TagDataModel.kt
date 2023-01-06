package com.jetapps.jettaskboard.model.search


import com.google.gson.annotations.SerializedName

data class TagDataModel(
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("source")
    val sourceDataModel: SourceDataModel? = SourceDataModel()
)