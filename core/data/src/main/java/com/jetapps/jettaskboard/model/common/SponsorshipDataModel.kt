package com.jetapps.jettaskboard.model.common


import com.google.gson.annotations.SerializedName
import com.jetapps.jettaskboard.model.common.SponsorDatModel

data class SponsorshipDataModel(
    @SerializedName("impression_urls")
    val impressionUrls: List<Any>? = listOf(),
    @SerializedName("tagline")
    val tagline: String? = "",
    @SerializedName("tagline_url")
    val taglineUrl: String? = "",
    @SerializedName("sponsor")
    val sponsor: SponsorDatModel? = SponsorDatModel()
)