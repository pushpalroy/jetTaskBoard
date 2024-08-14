package com.jetapps.jettaskboard

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.ui.Scaffold
import com.jetapps.jettaskboard.component.DashboardAppBar
import com.jetapps.jettaskboard.component.multifab.FabIcon
import com.jetapps.jettaskboard.component.multifab.FabOption
import com.jetapps.jettaskboard.component.multifab.MultiFabItem
import com.jetapps.jettaskboard.component.multifab.MultiFloatingActionButton
import com.jetapps.jettaskboard.drawer.JtbDrawer
import com.jetapps.jettaskboard.feature.dashboard.R.drawable
import com.jetapps.jettaskboard.navigation.JtbDrawerShape
import kotlinx.coroutines.launch

@Composable
fun DashboardRoute(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel(),
    navigateToTaskBoard: (String) -> Unit = {},
    navigateToCreateCard: (String) -> Unit = {},
    navigateToCreateBoard: (String) -> Unit = {},
    navigateToSearchScreen: (String) -> Unit = {},
    isExpandedScreen: Boolean
) {
    var isMenuClickedInExpandedMode by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.apply {
            getBoardListData()
        }
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val scaffoldState = rememberSizeAwareScaffoldState(isExpandedScreen)
        val scope = rememberCoroutineScope()

        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier,
            topBar = {
                DashboardAppBar(
                    onMenuIconClick = {
                        scope.launch {
                            if (isExpandedScreen.not()) {
                                scaffoldState.drawerState.open()
                            } else {
                                isMenuClickedInExpandedMode = isMenuClickedInExpandedMode.not()
                            }
                        }
                    },
                    onSearchIconClicked = {
                        navigateToSearchScreen("")
                    },
                    onNotificationIconClicked = {}
                )
            },
            // Gestures are enabled only on smaller and medium screens
            drawerGesturesEnabled = isExpandedScreen.not(),
            drawerShape = JtbDrawerShape(),
            drawerContent = {
                // Modal drawer is available only on smaller and medium screens
                if (isExpandedScreen.not()) {
                    JtbDrawer(
                        modifier = Modifier.fillMaxSize(),
                        viewModel = viewModel,
                        isExpandedScreen = isExpandedScreen
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                MultiFab(
                    navigateToCreateCard,
                    navigateToCreateBoard
                )
            }
        ) { scaffoldPadding ->
            val permanentNavDrawerWidth by animateDpAsState(
                targetValue = if (isMenuClickedInExpandedMode) 80.dp else 320.dp,
                animationSpec = SpringSpec(
                    dampingRatio = 0.5f,
                    stiffness = Spring.StiffnessLow
                )
            )
            Row(Modifier.fillMaxSize()) {
                // Show permanent drawer only for large screens
                if (isExpandedScreen) {
                    Column(
                        Modifier.width(permanentNavDrawerWidth)
                    ) {
                        Spacer(modifier = Modifier.height(56.dp))
                        JtbDrawer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            viewModel = viewModel,
                            isExpandedScreen = true,
                            isMenuClickedInExpandedMode = isMenuClickedInExpandedMode
                        )
                    }
                }
                AdaptiveDashboardContent(
                    viewModel = viewModel,
                    isExpandedScreen = isExpandedScreen,
                    contentPadding = scaffoldPadding,
                    navigateToTaskBoard = navigateToTaskBoard
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveDashboardContent(
    isExpandedScreen: Boolean,
    contentPadding: PaddingValues,
    viewModel: DashboardViewModel,
    navigateToTaskBoard: (String) -> Unit = {}
) {
//    val navigator = rememberSupportingPaneScaffoldNavigator<String>()
    val navigator = rememberListDetailPaneScaffoldNavigator<String>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(
        modifier = Modifier.padding(contentPadding),
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                DashboardMainPaneContent(
                    isExpanded = isExpandedScreen,
                    viewModel = viewModel,
                    navigateToTaskBoard = navigateToTaskBoard
                )
            }
        },
        detailPane = {
            AnimatedPane {
                DashboardDetailPane(
                    viewModel = viewModel,
                    navigateToTaskBoard = navigateToTaskBoard
                )
            }
        },
    )
}

@Composable
private fun rememberSizeAwareScaffoldState(
    isExpandedScreen: Boolean
): ScaffoldState {
    val commonSnackBarHostState = remember { SnackbarHostState() }
    val compactScaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(DrawerValue.Closed),
        snackbarHostState = commonSnackBarHostState
    )
    val expandedScaffoldState = rememberScaffoldState(
        drawerState = DrawerState(DrawerValue.Closed),
        snackbarHostState = commonSnackBarHostState
    )
    return if (isExpandedScreen) {
        expandedScaffoldState
    } else {
        compactScaffoldState
    }
}

@Composable
private fun MultiFab(
    navigateToCreateCard: (String) -> Unit = {},
    navigateToCreateBoard: (String) -> Unit = {}
) {
    MultiFloatingActionButton(
        fabIcon = FabIcon(
            iconRes = drawable.ic_edit,
            iconRotate = 25f
        ),
        fabOption = FabOption(
            iconTint = Color.White,
            showLabels = true
        ),
        items = listOf(
            MultiFabItem(
                id = 1,
                iconRes = drawable.dashboard_icon,
                label = "Board"
            ),
            MultiFabItem(
                id = 2,
                iconRes = drawable.card_icon,
                label = "Card"
            )
        ),
        onFabItemClicked = { item ->
            if (item.id == 1) {
                navigateToCreateBoard("")
            } else {
                navigateToCreateCard("")
            }
        }
    )
}
