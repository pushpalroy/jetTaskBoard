package com.jetapps.jettaskboard.model

import com.google.gson.annotations.SerializedName

data class RandomPhotoItemDataModel(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("created_at")
    var createdAt: String? = null,
    @SerializedName("updated_at")
    var updatedAt: String? = null,
    @SerializedName("promoted_at")
    var promotedAt: String? = null,
    @SerializedName("width")
    var width: Int? = null,
    @SerializedName("height")
    var height: Int? = null,
    @SerializedName("color")
    var color: String? = null,
    @SerializedName("blur_hash")
    var blurHash: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("alt_description")
    var altDescription: Any? = null,
    @SerializedName("urls")
    var urlDataModel: UrlDataModel? = null,
    @SerializedName("links")
    var linkDataModel: LinkDataModel? = null,
    @SerializedName("likes")
    var likes: Int? = null,
    @SerializedName("liked_by_user")
    var likedByUser: Boolean? = null,
    @SerializedName("current_user_collections")
    var currentUserCollections: List<Any>? = null,
    @SerializedName("sponsorship")
    var sponsorshipDataModel: SponsorshipDataModel? = null,
    @SerializedName("topic_submissions")
    var topicSubmissionsDataModel: TopicSubmissionsDataModel? = null,
    @SerializedName("user")
    var userDataModel: UserDataModel? = null
)