package com.jetapps.jettaskboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DashboardRoute(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel(),
    navigateToTaskBoard: (String) -> Unit = {},
    navigateToCreateCard: (String) -> Unit = {},
    navigateToCreateBoard: (String) -> Unit = {},
    isExpandedScreen: Boolean,
) {
  Surface(
    modifier = modifier.fillMaxSize(),
    color = MaterialTheme.colors.background
  ) {
    Button(
      modifier = Modifier.padding(16.dp),
      onClick = { navigateToTaskBoard("") }
    ) {
      Text(text = "This is dummy dashboard.\n\nGo to task board")
    }
  }
}