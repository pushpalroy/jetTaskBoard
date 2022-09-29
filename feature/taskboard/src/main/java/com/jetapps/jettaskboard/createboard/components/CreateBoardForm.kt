package com.jetapps.jettaskboard.createboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.CreateFormDropDown

@Composable
fun CreateBoardForm() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
    ) {
        val workspaceList = mapOf(
            "Praxis" to "Demo Application to learn latest android best practices",
            "Praxis-Flutter" to "Demo Application to learn latest Flutter best practices",
            "Trello Clone" to "Trello Clone built using Compose.",
        )

        val visibilityList = mapOf(
            "Workspace" to "Anyone to the users Workspace can see this board",
            "Public" to "The board is public. It's visible to anyone with the link and will show up to anyone with google link",
            "Private" to "the board is private. Only people added to the board can view and edit it",
        )

        CreateFormEditText(hint = "Board Name")

        CreateFormDropDown(
            headingText = "Workspace",
            contentMap = workspaceList,
        )

        CreateFormDropDown(
            headingText = "Visibility",
            contentMap = visibilityList,
        )
    }
}