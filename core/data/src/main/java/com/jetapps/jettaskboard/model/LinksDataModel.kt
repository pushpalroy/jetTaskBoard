package com.jetapps.jettaskboard.model


import com.google.gson.annotations.SerializedName

data class LinksDataModel(
    @SerializedName("self")
    var self: String?,
    @SerializedName("html")
    var html: String?,
    @SerializedName("photos")
    var photos: String?,
    @SerializedName("likes")
    var likes: String?,
    @SerializedName("portfolio")
    var portfolio: String?,
    @SerializedName("following")
    var following: String?,
    @SerializedName("followers")
    var followers: String?
)