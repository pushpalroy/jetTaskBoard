package com.jetapps.jettaskboard.ui

import androidx.compose.runtime.Composable
import com.jetapps.jettaskboard.navigation.JtbNavHost
import com.jetapps.jettaskboard.theme.JtbTheme

@Composable
fun JtbApp(
  appState: JtbAppState = rememberJtbAppState(),
  isExpandedScreen: Boolean,
) {
  JtbTheme(darkTheme = true) {
    JtbNavHost(
      navController = appState.navController,
      onNavigateToDestination = appState::navigate,
      onBackClick = appState::onBackClick,
      isExpandedScreen = isExpandedScreen
    )
  }
}