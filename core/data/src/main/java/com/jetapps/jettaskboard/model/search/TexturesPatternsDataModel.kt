package com.jetapps.jettaskboard.model.search


import com.google.gson.annotations.SerializedName

data class TexturesPatternsDataModel(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("approved_on")
    val approvedOn: String? = null
)