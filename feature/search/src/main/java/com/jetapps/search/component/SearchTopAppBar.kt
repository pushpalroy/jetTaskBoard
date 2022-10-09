package com.jetapps.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jetapps.search.presentation.SearchScreenViewModel

@Composable
fun SearchTopAppBar(
    onCancelClick: () -> Unit,
    searchQueryEntered: (String) -> Unit,
    searchScreenViewModel: SearchScreenViewModel
) {
    TopAppBar(
        title = {
            TextField(
                modifier = Modifier
                    .background(Color.Transparent)
                    .fillMaxWidth(),
                value = searchScreenViewModel.inputValue.value,
                onValueChange = {
                    searchScreenViewModel.inputValue.value = it
                    searchQueryEntered(it.toString())
                },
                placeholder = {
                    Text(text = "Search..")
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = onCancelClick) {
                Icon(Icons.Filled.Clear, "Cancel")
            }
        }
    )
}