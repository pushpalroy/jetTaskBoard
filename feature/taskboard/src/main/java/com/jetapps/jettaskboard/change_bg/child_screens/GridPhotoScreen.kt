package com.jetapps.jettaskboard.change_bg.child_screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.component.PhotoCardComponent
import com.jetapps.jettaskboard.model.ChangeBackgroundPhotoModel

@Composable
fun GridPhotoScreen(
    photoList: List<ChangeBackgroundPhotoModel> = emptyList(),
    onImageSelected: (url: String) -> Unit,
) {
    LazyVerticalGrid(
        modifier = Modifier
            .padding(top = 4.dp, bottom = 8.dp),
        columns = GridCells.Adaptive(minSize = 150.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        if (photoList.isNotEmpty()) {
            items(photoList.subList(0, 5)) { photoItem ->
                PhotoCardComponent(
                    modifier = Modifier.clickable {
                        onImageSelected(photoItem.imageUrl ?: "")
                    },
                    title = photoItem.imageName ?: "",
                    backgroundImageUrl = photoItem.imageUrl ?: ""
                )
            }
        }
    }
}