package com.jetapps.jettaskboard.model


import com.google.gson.annotations.SerializedName

data class SponsorshipDataModel(
    @SerializedName("impression_urls")
    var impressionUrls: List<Any>?,
    @SerializedName("tagline")
    var tagline: String?,
    @SerializedName("tagline_url")
    var taglineUrl: String?,
    @SerializedName("sponsor")
    var sponsorDatModel: SponsorDatModel?
)