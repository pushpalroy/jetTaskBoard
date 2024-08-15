package com.jetapps.jettaskboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.window.core.layout.WindowWidthSizeClass
import com.jetapps.jettaskboard.ui.JtbApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val adaptiveInfo = currentWindowAdaptiveInfo()
            val isExpandedScreen = with(adaptiveInfo) {
                windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED
            }

            JtbApp(isExpandedScreen = isExpandedScreen)
        }
    }
}
