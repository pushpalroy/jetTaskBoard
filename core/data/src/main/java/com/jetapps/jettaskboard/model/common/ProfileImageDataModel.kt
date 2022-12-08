package com.jetapps.jettaskboard.model.common

import com.google.gson.annotations.SerializedName

data class ProfileImageDataModel(
    @SerializedName("small")
    val small: String? = null,
    @SerializedName("medium")
    val medium: String? = null,
    @SerializedName("large")
    val large: String? = null
)