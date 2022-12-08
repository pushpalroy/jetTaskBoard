package com.jetapps.jettaskboard.change_bg.child_screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.component.PhotoCardComponent
import com.jetapps.jettaskboard.model.ChangeBackgroundPhotoModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GridPhotoScreen(
    photoList: List<ChangeBackgroundPhotoModel> = emptyList(),
    onImageSelected: (url: String) -> Unit,
) {
    var imageItemSelected by remember { mutableStateOf(-1) }
    var startLoadingAnimation by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LazyVerticalGrid(
        modifier = Modifier
            .padding(top = 4.dp, bottom = 8.dp),
        columns = GridCells.Adaptive(minSize = 150.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        if (photoList.isNotEmpty()) {
            itemsIndexed(photoList) { index, photoItem ->
                PhotoCardComponent(
                    modifier = Modifier.clickable {
                        imageItemSelected = if (imageItemSelected != index) {
                            index
                        } else {
                            -1
                        }
                        scope.launch {
                            startLoadingAnimation = true
                            delay(3000)
                            startLoadingAnimation = false
                            onImageSelected(photoItem.imageUrl ?: "")
                        }
                    },
                    title = photoItem.imageName ?: "",
                    backgroundImageUrl = photoItem.imageUrl ?: "",
                    imageSelectedState = imageItemSelected == index,
                    loadingAnimationState = startLoadingAnimation
                )
            }
        }
    }
}