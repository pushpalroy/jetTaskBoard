package com.jetapps.jettaskboard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trelloclonelist.carddetailscomponents.CardDetailsContent
import com.example.trelloclonelist.carddetailscomponents.MotionTopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CardDetailsRoute(
    modifier: Modifier = Modifier,
    viewModel: CardViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val scrollState = rememberScrollState()
            Scaffold(topBar = { MotionTopBar(scrollState = scrollState) }) {
                CardDetailsContent(scrollState)
            }
        }
    }
}