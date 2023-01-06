package com.jetapps.jettaskboard.model.common

import com.google.gson.annotations.SerializedName
import com.jetapps.jettaskboard.model.common.LinksDataModel
import com.jetapps.jettaskboard.model.common.ProfileImageDataModel
import com.jetapps.jettaskboard.model.common.SocialDataModel

data class SponsorDatModel(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("username")
    val username: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("first_name")
    val firstName: String? = "",
    @SerializedName("last_name")
    val lastName: Any? = Any(),
    @SerializedName("twitter_username")
    val twitterUsername: String? = "",
    @SerializedName("portfolio_url")
    val portfolioUrl: String? = "",
    @SerializedName("bio")
    val bio: String? = "",
    @SerializedName("location")
    val location: String? = "",
    @SerializedName("links")
    val links: LinksDataModel? = LinksDataModel(),
    @SerializedName("profile_image")
    val profileImage: ProfileImageDataModel? = ProfileImageDataModel(),
    @SerializedName("instagram_username")
    val instagramUsername: String? = "",
    @SerializedName("total_collections")
    val totalCollections: Int? = 0,
    @SerializedName("total_likes")
    val totalLikes: Int? = 0,
    @SerializedName("total_photos")
    val totalPhotos: Int? = 0,
    @SerializedName("accepted_tos")
    val acceptedTos: Boolean? = false,
    @SerializedName("for_hire")
    val forHire: Boolean? = false,
    @SerializedName("social")
    val social: SocialDataModel? = SocialDataModel()
)