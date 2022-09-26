package com.jetapps.jettaskboard.createboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val workspaceList = mapOf(
    "My user's workspace" to ""
)

val visibilityList = mapOf(
    "Private" to "the board is private. Only people added to the board can view and edit it",
    "Workspace" to "Anyone to the user;s Workspace can see this board",
    "Public" to "The board is public. It's visible to anyone with the link and will show up to anyone with google link"
)

@Composable
fun CreateBoardForm() {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .background(Color.Black)
            .fillMaxHeight()
    ) {
        CreateFormEditText(hint = "Board Name")
        CreateFormText(text = "Workspace")

        CreateformDropDown(
            text = "User's Workspace",
            modifier = Modifier.padding(15.dp),
            contentMap = workspaceList
        )
        CreateFormText(text = "Visibility")
        CreateformDropDown(
            text = "User's Workspace",
            modifier = Modifier.padding(15.dp),
            contentMap = visibilityList
        )
    }
}