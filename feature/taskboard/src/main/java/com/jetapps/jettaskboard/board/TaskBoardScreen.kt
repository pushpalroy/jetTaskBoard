package com.jetapps.jettaskboard.board

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetapps.jettaskboard.TaskBoardViewModel
import com.jetapps.jettaskboard.feature.taskboard.R
import com.jetapps.jettaskboard.theme.DefaultTaskBoardBGColor
import com.jetapps.jettaskboard.zoomable.Zoomable
import com.jetapps.jettaskboard.zoomable.rememberZoomableState
import kotlinx.coroutines.launch

@Composable
fun TaskBoardRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    isExpandedScreen: Boolean,
    navigateToCreateCard: (String) -> Unit = {},
    viewModel: TaskBoardViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val zoomableState = rememberZoomableState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                onBackClick = onBackClick,
                title = viewModel.boardInfo.value.second
            )
        },
        floatingActionButton = {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FAB(
                    onClick = {},
                    R.drawable.ic_zoom_in
                )
                FAB(
                    onClick = {
                        if (zoomableState.scale.value != 1f) {
                            coroutineScope.launch {
                                zoomableState.animateBy(
                                    zoomChange = 1 / zoomableState.scale.value,
                                    panChange = -zoomableState.offset.value,
                                    rotationChange = -zoomableState.rotation.value
                                )
                            }
                        }
                    },
                    R.drawable.ic_zoom_out
                )
            }
        }
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize(),
            color = DefaultTaskBoardBGColor
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(R.drawable.bg_board),
                    contentDescription = "background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .matchParentSize()
                )
                Zoomable(
                    coroutineScope = coroutineScope,
                    zoomableState = zoomableState
                ) {
                    Board(
                        modifier = Modifier.fillMaxSize(),
                        navigateToCreateCard = navigateToCreateCard,
                        viewModel = viewModel,
                        isExpandedScreen = isExpandedScreen
                    )
                }
            }
        }
    }
}

@Composable
fun TopAppBar(
    onBackClick: () -> Unit,
    title: String
) {
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        title = { Text(text = title) }
    )
}

@Composable
fun FAB(
    onClick: () -> Unit,
    painter: Int
) {
    FloatingActionButton(onClick = onClick) {
        Icon(
            modifier = Modifier,
            painter = painterResource(id = painter),
            tint = Color.White,
            contentDescription = null
        )
    }
}
