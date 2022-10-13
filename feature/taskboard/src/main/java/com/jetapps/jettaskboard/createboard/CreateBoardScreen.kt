package com.jetapps.jettaskboard.createboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jetapps.jettaskboard.createboard.components.CreateBoardForm
import com.jetapps.jettaskboard.createboard.components.CreateBoardTopBar

@Composable
fun CreateBoardRoute(
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(topBar = {
        CreateBoardTopBar(
            title = "Create Board",
            onCancelClick = onCancelClick
        )
    }) {
        Column(modifier = Modifier.padding(it)) {
            CreateBoardForm()
        }
    }
}
