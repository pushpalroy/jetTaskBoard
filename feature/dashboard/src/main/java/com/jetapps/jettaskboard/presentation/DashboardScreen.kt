package com.jetapps.jettaskboard.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.ui.Scaffold
import com.jetapps.jettaskboard.component.DashboardAppBar
import com.jetapps.jettaskboard.component.JtbDrawer
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun DashboardRoute(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    /**
     * TODO : Define Theme and Background
     */
    // A surface container using the 'background' color from the theme
    Surface(modifier = modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

        val scaffoldState: ScaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()

        Scaffold(
            scaffoldState = scaffoldState,
            modifier = modifier,
            topBar = {
                DashboardAppBar(
                    onMenuIconClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    },
                    onSearchIconClicked = { /*TODO*/ },
                    onNotificationIconClicked = {}
                )
            },
            drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
            drawerContent = {
                JtbDrawer(modifier = modifier)
            }
        ) {}
    }
}