package com.jetapps.jettaskboard.model

data class ChangeBackgroundPhotoModel(
    var isImageSelected: Boolean = false,
    val imageUrl: String? = null,
    val imageName: String? = null,
    val isImageHoveredAbove: Boolean? = false,
)
