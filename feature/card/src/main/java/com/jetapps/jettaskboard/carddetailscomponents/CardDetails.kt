package com.example.trelloclonelist.carddetailscomponents

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CardDetails() {
    Surface(modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background) {
        val scrollState = rememberScrollState()
        Scaffold(topBar = { MotionTopBar(scrollState = scrollState) }) {
            CardDetailsContent(scrollState)
        }
//        Column() {
//            TopBar()
//        }

    }
}




