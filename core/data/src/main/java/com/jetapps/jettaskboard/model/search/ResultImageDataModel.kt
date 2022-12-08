package com.jetapps.jettaskboard.model.search

import com.google.gson.annotations.SerializedName
import com.jetapps.jettaskboard.model.common.LinkDataModel
import com.jetapps.jettaskboard.model.common.LinksDataModel
import com.jetapps.jettaskboard.model.common.SponsorshipDataModel
import com.jetapps.jettaskboard.model.common.UrlDataModel
import com.jetapps.jettaskboard.model.common.UserDataModel
import com.jetapps.jettaskboard.model.random.TopicSubmissionsDataModel

data class ResultImageDataModel(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("promoted_at")
    val promotedAt: Any? = Any(),
    @SerializedName("width")
    val width: Int? = 0,
    @SerializedName("height")
    val height: Int? = 0,
    @SerializedName("color")
    val color: String? = "",
    @SerializedName("blur_hash")
    val blurHash: String? = "",
    @SerializedName("description")
    val description: Any? = Any(),
    @SerializedName("alt_description")
    val altDescription: String? = "",
    @SerializedName("urls")
    val urls: UrlDataModel? = UrlDataModel(),
    @SerializedName("links")
    val links: LinkDataModel? = LinkDataModel(),
    @SerializedName("likes")
    val likes: Int? = 0,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean? = false,
    @SerializedName("current_user_collections")
    val currentUserCollections: List<Any>? = listOf(),
    @SerializedName("sponsorship")
    val sponsorship: SponsorshipDataModel? = SponsorshipDataModel(),
    @SerializedName("topic_submissions")
    val topicSubmissions: TopicSubmissionsDataModel? = TopicSubmissionsDataModel(),
    @SerializedName("user")
    val user: UserDataModel? = UserDataModel(),
    @SerializedName("tags")
    val tagDataModels: List<TagDataModel>? = listOf()
)