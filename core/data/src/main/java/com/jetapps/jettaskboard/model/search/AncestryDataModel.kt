package com.jetapps.jettaskboard.model.search


import com.google.gson.annotations.SerializedName

data class AncestryDataModel(
    @SerializedName("type")
    val typeDataModel: TypeDataModel? = null,
    @SerializedName("category")
    val categoryDataModel: CategoryDataModel? = null,
    @SerializedName("subcategory")
    val subcategoryDataModel: SubcategoryDataModel? = null
)