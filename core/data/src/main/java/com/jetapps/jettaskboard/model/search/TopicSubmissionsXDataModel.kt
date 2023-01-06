package com.jetapps.jettaskboard.model.search

import com.google.gson.annotations.SerializedName

data class TopicSubmissionsXDataModel(
    @SerializedName("textures-patterns")
    val texturesPatternsDataModel: TexturesPatternsDataModel? = null
)