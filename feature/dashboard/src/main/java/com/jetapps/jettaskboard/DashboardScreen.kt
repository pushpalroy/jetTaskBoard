package com.jetapps.jettaskboard

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldLayout
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowWidthSizeClass
import com.google.accompanist.insets.ui.Scaffold
import com.jetapps.jettaskboard.component.DashboardAppBar
import com.jetapps.jettaskboard.component.NavigationTrailItems
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
    navigateToCreateBoard: () -> Unit = {},
    navigateToSearchScreen: (String) -> Unit = {},
    isExpandedScreen: Boolean
) {
    val scaffoldState = rememberSizeAwareScaffoldState(isExpandedScreen)
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val scope = rememberCoroutineScope()
    var selectedItemIndex by remember { mutableIntStateOf(0) }


    val customNavSuiteType = with(adaptiveInfo) {
        if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
            NavigationSuiteType.NavigationRail
        } else {
            NavigationSuiteType.None
        }
    }


    // Todo(Niket) : Write a custom Nav Drawer to provide pixel perfect animations
//    val permanentNavDrawerWidth by animateDpAsState(
//        targetValue = if (scaffoldState.drawerState.isClosed) 0.dp else 320.dp,
//        animationSpec = SpringSpec(
//            dampingRatio = 0.5f,
//            stiffness = Spring.StiffnessHigh
//        ), label = "nav_drawer_width"
//    )

    // Todo(Niket): Move this to VM
//    LaunchedEffect(Unit) {
//        viewModel.apply {
//            getBoardListData()
//        }
//    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        NavigationSuiteScaffoldLayout(
            layoutType = customNavSuiteType,
            navigationSuite = {
                if (customNavSuiteType == NavigationSuiteType.NavigationRail) {
                    NavigationRail {
                        Spacer(Modifier.weight(1f))
                        NavigationTrailItems.entries.forEachIndexed { index, navigationTrailItems ->
                            NavigationRailItem(
                                selected = index == selectedItemIndex,
                                onClick = {
                                    selectedItemIndex = index
                                },
                                icon = {
                                    Icon(
                                        imageVector = navigationTrailItems.icon,
                                        contentDescription = navigationTrailItems.title
                                    )
                                },
                                label = {
                                    Text(text = navigationTrailItems.title)
                                }
                            )
                        }
                        Spacer(Modifier.weight(1f))
                    }
                }
            },
            content = {
                Scaffold(
                    scaffoldState = scaffoldState,
                    modifier = Modifier,
                    topBar = {
                        DashboardAppBar(
                            onMenuIconClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            },
                            onSearchIconClicked = {
                                navigateToSearchScreen("")
                            },
                            onNotificationIconClicked = {}
                        )
                    },
                    drawerGesturesEnabled = true,
                    drawerShape = JtbDrawerShape(),
                    drawerContent = {
                        JtbDrawer(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(),
                            viewModel = viewModel,
                        )
                    },
                    floatingActionButtonPosition = FabPosition.End,
                    floatingActionButton = {
                        MultiFab(
                            navigateToCreateCard,
                            navigateToCreateBoard
                        )
                    }
                ) { scaffoldPadding ->
                    AdaptiveDashboardContent(
                        viewModel = viewModel,
                        isExpandedScreen = isExpandedScreen,
                        contentPadding = scaffoldPadding,
                        navigateToTaskBoard = navigateToTaskBoard,
                        createBoard = navigateToCreateBoard,
                    )
                }
            },
        )
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveDashboardContent(
    isExpandedScreen: Boolean,
    contentPadding: PaddingValues,
    viewModel: DashboardViewModel,
    navigateToTaskBoard: (String) -> Unit = {},
    createBoard : () -> Unit,
) {
    //Todo(Niket): Write a detail blog on PaneScaffold Api
//    val navigator = rememberSupportingPaneScaffoldNavigator<String>()
    val navigator = rememberListDetailPaneScaffoldNavigator<String>()
    val boardList by viewModel.listOfBoards.collectAsStateWithLifecycle()

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
                    navigateToTaskBoard = navigateToTaskBoard,
                    boardList = boardList,
                    createBoard = createBoard
                )
            }
        },
        detailPane = {
            AnimatedPane {
                DashboardDetailPane(
                    navigateToTaskBoard = navigateToTaskBoard,
                    boardList,
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
    navigateToCreateBoard: () -> Unit = {}
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
                navigateToCreateBoard()
            } else {
                navigateToCreateCard("")
            }
        }
    )
}
