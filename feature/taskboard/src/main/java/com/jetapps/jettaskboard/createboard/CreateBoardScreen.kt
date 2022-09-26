package com.jetapps.jettaskboard.createboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jetapps.jettaskboard.createboard.components.CreateBoardForm
import com.jetapps.jettaskboard.createboard.components.CreateBoardTopBar

@Composable
fun CreateBoardRoute(
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(topBar = {
            CreateBoardTopBar(
                title = "Create Board",
                onCancelClick = onCancelClick
            )
        }){
            CreateBoardForm()
        }
    }
}