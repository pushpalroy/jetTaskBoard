package com.jetapps.jettaskboard.model.common

import com.google.gson.annotations.SerializedName

data class LinkDataModel(
    @SerializedName("self")
    val self: String? = null,
    @SerializedName("html")
    val html: String? = null,
    @SerializedName("download")
    val download: String? = null,
    @SerializedName("download_location")
    val downloadLocation: String? = null
)