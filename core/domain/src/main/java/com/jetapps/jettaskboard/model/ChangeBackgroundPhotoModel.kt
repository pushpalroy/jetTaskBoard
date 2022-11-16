package com.jetapps.jettaskboard.model

data class ChangeBackgroundPhotoModel(
    val imageUrl: String? = null,
    val imageName: String? = null,
    val isImageSelected: Boolean? = false,
    val isImageHoveredAbove: Boolean? = false
)
