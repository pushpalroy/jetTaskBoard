package com.jetapps.jettaskboard.change_bg

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetapps.jettaskboard.change_bg.child_screens.GridPhotoScreen
import com.jetapps.jettaskboard.change_bg.child_screens.StaticChangeBackgroundScreen

@Composable
fun ChangeBoardBackgroundRoute(
    modifier: Modifier = Modifier,
    isExpandedScreen: Boolean,
    onBackClick: () -> Unit,
    changeBoardBackgroundViewModel: ChangeBoardBackgroundViewModel = hiltViewModel()
) {
    val changingScreenState = changeBoardBackgroundViewModel.state.value
    val randomPhotoList = changeBoardBackgroundViewModel.randomPhotoList.value
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            androidx.compose.material.TopAppBar(
                modifier = Modifier,
                title = {
                    Text(text = "Change Background")
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { scaffoldPaddingValues ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(scaffoldPaddingValues)
        ) {
            when (changingScreenState) {
                ChangeBackgroundScreenState.STATIC_SCREEN -> {
                    StaticChangeBackgroundScreen { selectedScreenState ->
                        changeBoardBackgroundViewModel.changeScreenState(selectedScreenState)
                        // Make API Call
                        changeBoardBackgroundViewModel.generateRandomPhotoList()
                    }
                }
                ChangeBackgroundScreenState.PHOTO_SCREEN -> {
                    randomPhotoList?.let { safeRandomPhotoList ->
                        GridPhotoScreen(
                            photoList = safeRandomPhotoList,
                            onImageSelected = { selectedImageUrl ->
                                // Store it to the Data Store
                            })
                    }
                }
                ChangeBackgroundScreenState.COLORS_SCREEN -> {
                    Toast.makeText(context, "Feature in Progress", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}