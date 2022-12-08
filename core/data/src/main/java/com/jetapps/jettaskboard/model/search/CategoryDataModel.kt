package com.jetapps.jettaskboard.model.search


import com.google.gson.annotations.SerializedName

data class CategoryDataModel(
    @SerializedName("slug")
    val slug: String? = null,
    @SerializedName("pretty_slug")
    val prettySlug: String? = null
)