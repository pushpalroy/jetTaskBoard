package com.jetapps.jettaskboard.model.common


import com.google.gson.annotations.SerializedName

data class UrlDataModel(
    @SerializedName("raw")
    val raw: String? = null,
    @SerializedName("full")
    val full: String? = null,
    @SerializedName("regular")
    val regular: String? = null,
    @SerializedName("small")
    val small: String? = null,
    @SerializedName("thumb")
    val thumb: String? = null,
    @SerializedName("small_s3")
    val smallS3: String? = null
)