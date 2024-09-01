package com.jetapps.jettaskboard.createboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetapps.jettaskboard.createboard.CreateBoardViewModel.Companion.visibilityList
import com.jetapps.jettaskboard.createboard.components.CreateBoardTopBar
import com.jetapps.jettaskboard.createboard.components.CreateFormEditText
import com.jetapps.jettaskboard.model.BoardModel
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import com.jetapps.jettaskboard.CreateVisibilityFormDropDown

@Composable
fun CreateBoardRoute(
    modifier: Modifier = Modifier,
    createBoardViewModel: CreateBoardViewModel = hiltViewModel(),
    onCancelClick: () -> Unit,
    navigateToTaskBoardScreen: (Long) -> Unit,
) {
    var boardTitle by remember { mutableStateOf("") }

    Scaffold(topBar = {
        CreateBoardTopBar(
            title = "Create Board",
            onCancelClick = onCancelClick,
            onCreateBoardClick = {
                val timestampAsId = System.currentTimeMillis()
                if (boardTitle.isBlank().not()) {
                    createBoardViewModel.createNewBoard(
                        BoardModel(
                            id = timestampAsId,
                            title = boardTitle
                        )
                    )
                    boardTitle = ""
                    navigateToTaskBoardScreen(timestampAsId)
                }
            }
        )
    }) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .background(MaterialTheme.colors.background)
                .fillMaxSize()
                .padding(24.dp),
        ) {
            CreateFormEditText(
                hint = "New Task Board name",
                onValueChanged = { value ->
                    boardTitle = value
                }
            )

            Spacer(modifier = modifier.height(24.dp))

            CreateVisibilityFormDropDown(
                headingText = "Visibility",
                contentMap = visibilityList
            )
        }
    }
}
