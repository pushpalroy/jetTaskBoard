package com.jetapps.jettaskboard.createboard.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable

@Composable
fun CreateBoardTopBar(
    title: String,
    onCancelClick: () -> Unit,
    onCreateBoardClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onCancelClick) {
                Icon(Icons.Filled.Clear, "Cancel")
            }
        },
        actions = {
            IconButton(onClick = onCreateBoardClick, enabled = true) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Create board")
            }
        }
    )
}
