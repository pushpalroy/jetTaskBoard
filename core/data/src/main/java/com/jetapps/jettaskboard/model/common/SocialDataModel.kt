package com.jetapps.jettaskboard.model.common


import com.google.gson.annotations.SerializedName

data class SocialDataModel(
    @SerializedName("instagram_username")
    val instagramUsername: String? = null,
    @SerializedName("portfolio_url")
    val portfolioUrl: String? = null,
    @SerializedName("twitter_username")
    val twitterUsername: String? = null,
    @SerializedName("paypal_email")
    val paypalEmail: Any? = null
)