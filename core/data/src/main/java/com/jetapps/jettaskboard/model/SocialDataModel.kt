package com.jetapps.jettaskboard.model


import com.google.gson.annotations.SerializedName

data class SocialDataModel(
    @SerializedName("instagram_username")
    var instagramUsername: String?,
    @SerializedName("portfolio_url")
    var portfolioUrl: String?,
    @SerializedName("twitter_username")
    var twitterUsername: String?,
    @SerializedName("paypal_email")
    var paypalEmail: Any?
)