package com.jetapps.jettaskboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jetapps.jettaskboard.ui.JtbApp
import com.jetapps.jettaskboard.util.WindowSize
import com.jetapps.jettaskboard.util.rememberWindowSizeClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val windowSize = rememberWindowSizeClass()
      val isExpandedScreen = windowSize == WindowSize.Expanded
      JtbApp(isExpandedScreen = isExpandedScreen)
    }
  }
}