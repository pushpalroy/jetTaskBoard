package com.jetapps.jettaskboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CreateCardRoute(
  isExpandedScreen: Boolean,
  modifier: Modifier = Modifier,
  viewModel: CardViewModel = hiltViewModel(),
) {
  Surface(
    modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colors.background
  ) {
    Text(text = "Create Card")
  }
}