package com.jetapps.jettaskboard.model.search


import com.google.gson.annotations.SerializedName

data class SourceDataModel(
    @SerializedName("ancestry")
    val ancestryDataModel: AncestryDataModel? = AncestryDataModel(),
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("subtitle")
    val subtitle: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("meta_title")
    val metaTitle: String? = "",
    @SerializedName("meta_description")
    val metaDescription: String? = "",
    @SerializedName("cover_photo")
    val coverPhotoDataModel: CoverPhotoDataModel? = CoverPhotoDataModel()
)