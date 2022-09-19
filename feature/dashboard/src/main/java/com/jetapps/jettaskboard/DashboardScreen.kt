package com.jetapps.jettaskboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DashboardRoute(
  modifier: Modifier = Modifier,
  viewModel: DashboardViewModel = hiltViewModel()
) {
// A surface container using the 'background' color from the theme
  Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
    Greeting("Android")
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}