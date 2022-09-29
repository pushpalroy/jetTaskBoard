package com.jetapps.jettaskboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jetapps.jettaskboard.ui.JtbApp
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // WindowCompat.setDecorFitsSystemWindows(window, false)

    setContent {

      // We can also use a custom solution like:
      // val windowSize = rememberWindowSizeClass()
      // val isExpandedScreen = windowSize == WindowSize.Expanded

      val windowSize = calculateWindowSizeClass(this).widthSizeClass
      val isExpandedScreen = windowSize == WindowWidthSizeClass.Expanded
      JtbApp(isExpandedScreen = isExpandedScreen)
    }
  }
}