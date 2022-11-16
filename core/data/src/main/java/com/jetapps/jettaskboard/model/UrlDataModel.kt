package com.jetapps.jettaskboard.model


import com.google.gson.annotations.SerializedName

data class UrlDataModel(
    @SerializedName("raw")
    var raw: String?,
    @SerializedName("full")
    var full: String?,
    @SerializedName("regular")
    var regular: String?,
    @SerializedName("small")
    var small: String?,
    @SerializedName("thumb")
    var thumb: String?,
    @SerializedName("small_s3")
    var smallS3: String?
)