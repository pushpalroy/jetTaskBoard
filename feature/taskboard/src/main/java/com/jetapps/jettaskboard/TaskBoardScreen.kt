package com.jetapps.jettaskboard

import androidx.compose.foundation.layout.Box
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
fun TaskBoardRoute(
  onBackClick: () -> Unit,
  modifier: Modifier = Modifier,
  viewModel: TaskBoardViewModel = hiltViewModel()
) {
  Surface(
    modifier = modifier.fillMaxSize(),
    color = MaterialTheme.colors.background
  ) {
    Box {
      Button(
        modifier = Modifier.padding(16.dp),
        onClick = onBackClick
      ) {
        Text(text = "Go to back to dash board")
      }
    }
  }
}