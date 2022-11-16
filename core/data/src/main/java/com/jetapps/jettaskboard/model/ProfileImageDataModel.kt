package com.jetapps.jettaskboard.model


import com.google.gson.annotations.SerializedName

data class ProfileImageDataModel(
    @SerializedName("small")
    var small: String?,
    @SerializedName("medium")
    var medium: String?,
    @SerializedName("large")
    var large: String?
)