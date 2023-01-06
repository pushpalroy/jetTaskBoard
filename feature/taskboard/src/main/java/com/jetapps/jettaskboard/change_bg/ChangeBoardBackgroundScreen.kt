package com.jetapps.jettaskboard.change_bg

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetapps.jettaskboard.change_bg.child_screens.GridPhotoScreen
import com.jetapps.jettaskboard.change_bg.child_screens.StaticChangeBackgroundScreen
import com.jetapps.jettaskboard.component.SearchComponent
import com.jetapps.jettaskboard.loadingcomponents.TrelloLoadingAnimation
import com.jetapps.jettaskboard.util.UIState
import com.jetapps.jettaskboard.util.UnsplashCollection

@Composable
fun ChangeBoardBackgroundRoute(
    modifier: Modifier = Modifier,
    isExpandedScreen: Boolean = false,
    onBackClick: () -> Unit,
    changeBoardBackgroundViewModel: ChangeBoardBackgroundViewModel = hiltViewModel()
) {
    val changingScreenState = changeBoardBackgroundViewModel.state.value
    val randomPhotoList = changeBoardBackgroundViewModel.randomPhotoList.value
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val textSearch by changeBoardBackgroundViewModel.textSearch.collectAsState()

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
                        onClick = {
                            if (changingScreenState == ChangeBackgroundScreenState.STATIC_SCREEN) {
                                onBackClick()
                            } else {
                                changeBoardBackgroundViewModel.changeScreenState(
                                    ChangeBackgroundScreenState.STATIC_SCREEN
                                )
                            }
                        }
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
                        when (selectedScreenState) {
                            ChangeBackgroundScreenState.STATIC_SCREEN -> {}
                            ChangeBackgroundScreenState.PHOTO_SCREEN -> {
                                changeBoardBackgroundViewModel.generateRandomPhotoList(
                                    UnsplashCollection.RANDOM_NATURE_COLLECTION_ID
                                )
                            }
                            ChangeBackgroundScreenState.COLORS_SCREEN -> {
                                changeBoardBackgroundViewModel.generateRandomPhotoList(
                                    UnsplashCollection.RANDOM_COLORS_COLLECTION_ID
                                )
                            }
                        }
                        changeBoardBackgroundViewModel.changeScreenState(selectedScreenState)
                    }
                }
                ChangeBackgroundScreenState.PHOTO_SCREEN, ChangeBackgroundScreenState.COLORS_SCREEN -> {
                    Column(modifier = Modifier) {
                        SearchComponent(
                            textState = textSearch.orEmpty()
                        ) { query ->
                            changeBoardBackgroundViewModel.setSearchText(query)
                        }
                        when (randomPhotoList) {
                            UIState.Empty -> {}
                            is UIState.Failure -> {
                                Toast.makeText(
                                    LocalContext.current,
                                    "API Failure",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            UIState.Loading -> {
                                // Todo : Try `Box` instead of `Column`
                                Column(
                                    modifier = modifier
                                        .fillMaxSize()
                                        .padding(8.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    TrelloLoadingAnimation(
                                        sizeOfCanvas = 40.dp
                                    )
                                }
                            }
                            is UIState.Success -> {
                                randomPhotoList.data?.let { safeRandomPhotoList ->
                                    GridPhotoScreen(
                                        photoList = safeRandomPhotoList,
                                        onImageSelected = { selectedImageUrl ->
                                            changeBoardBackgroundViewModel.updateLatestBoardBgImgUri(
                                                selectedImageUrl
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}