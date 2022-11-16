package com.jetapps.jettaskboard.model


import com.google.gson.annotations.SerializedName

data class SponsorDatModel(
    @SerializedName("id")
    var id: String?,
    @SerializedName("updated_at")
    var updatedAt: String?,
    @SerializedName("username")
    var username: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("first_name")
    var firstName: String?,
    @SerializedName("last_name")
    var lastName: String?,
    @SerializedName("twitter_username")
    var twitterUsername: String?,
    @SerializedName("portfolio_url")
    var portfolioUrl: String?,
    @SerializedName("bio")
    var bio: String?,
    @SerializedName("location")
    var location: Any?,
    @SerializedName("links")
    var links: LinksDataModel?,
    @SerializedName("profile_image")
    var profileImageDataModel: ProfileImageDataModel?,
    @SerializedName("instagram_username")
    var instagramUsername: String?,
    @SerializedName("total_collections")
    var totalCollections: Int?,
    @SerializedName("total_likes")
    var totalLikes: Int?,
    @SerializedName("total_photos")
    var totalPhotos: Int?,
    @SerializedName("accepted_tos")
    var acceptedTos: Boolean?,
    @SerializedName("for_hire")
    var forHire: Boolean?,
    @SerializedName("social")
    var socialDataModel: SocialDataModel?
)