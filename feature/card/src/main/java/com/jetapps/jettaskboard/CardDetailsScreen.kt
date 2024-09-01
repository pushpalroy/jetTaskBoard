@file:OptIn(
    ExperimentalMaterial3AdaptiveApi::class,
    ExperimentalPermissionsApi::class,
    ExperimentalMaterial3AdaptiveApi::class,
)

package com.jetapps.jettaskboard

import android.Manifest
import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.jetapps.jettaskboard.carddetailscomponents.CardDetailExpandedPane
import com.jetapps.jettaskboard.carddetailscomponents.CardDetailsMainPane
import com.jetapps.jettaskboard.carddetailscomponents.components.MotionTopBar
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CardDetailsRoute(
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    viewModel: CardViewModel = hiltViewModel(),
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val scrollState = rememberScrollState()
        val expandedLeftScrollState = rememberScrollState()
        val expandedRightScrollState = rememberScrollState()
        val navigator = rememberListDetailPaneScaffoldNavigator<String>()
        val dialogState = rememberMaterialDialogState()
        val configuration = LocalConfiguration.current
        val context = LocalContext.current

        var imageUri: Uri? by remember {
            mutableStateOf<Uri?>(null)
        }

        val launcher = rememberLauncherForActivityResult(
            contract =
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            imageUri = uri
        }

        val galleryPermissionStatus =
            rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)


        BackHandler(navigator.canNavigateBack()) {
            navigator.navigateBack()
        }

        Scaffold(
            topBar = {
                MotionTopBar(
                    scrollState = scrollState,
                    isExpandedScreen,
                    onCancelClick,
                    viewModel.cardModel.coverImageUrl,
                    viewModel.cardModel.title,
                    updateCard = {
                        viewModel.updateCard()
                        onCancelClick()
                    }
                )
            }
        ) {
            ListDetailPaneScaffold(
                modifier = modifier,
                directive = navigator.scaffoldDirective,
                value = navigator.scaffoldValue,
                listPane = {
                    AnimatedPane {
                        CardDetailsMainPane(
                            viewModel = viewModel,
                            context = context,
                            configuration = configuration,
                            launcher = launcher,
                            scrollState = scrollState,
                            galleryPermissionState = galleryPermissionStatus,
                            imageUri = imageUri,
                            dialogState = dialogState,
                            isExpandedScreen = isExpandedScreen
                        )
                    }
                },
                detailPane = {
                    AnimatedPane {
                        CardDetailExpandedPane(
                            scrollState = scrollState,
                            viewModel = viewModel,
                            dialogState = dialogState,
                            galleryPermissionStatus = galleryPermissionStatus,
                            context = context,
                            launcher = launcher,
                        )
                    }
                },
            )
        }
    }
}
