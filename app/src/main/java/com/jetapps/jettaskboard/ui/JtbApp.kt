package com.jetapps.jettaskboard.ui

import androidx.compose.runtime.Composable
import com.jetapps.jettaskboard.navigation.JtbNavHost
import com.jetapps.jettaskboard.theme.JetTaskBoardTheme

@Composable
fun JtbApp(
  appState: JtbAppState = rememberJtbAppState()
) {
  JetTaskBoardTheme {
    JtbNavHost(
      navController = appState.navController,
      onNavigateToDestination = appState::navigate,
      onBackClick = {}
    )
  }
}