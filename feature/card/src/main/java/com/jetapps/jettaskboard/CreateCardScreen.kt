package com.jetapps.jettaskboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jetapps.jettaskboard.carddetailscomponents.ItemRow
import com.jetapps.jettaskboard.carddetailscomponents.TimeItemRow
import com.jetapps.jettaskboard.feature.card.R
import com.jetapps.jettaskboard.theme.DefaultTaskBoardBGColor
import com.jetapps.jettaskboard.theme.LabelOrange
import com.jetapps.jettaskboard.theme.SecondaryColor
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@Composable
fun CreateCardRoute(
    modifier: Modifier = Modifier,
    viewModel: CardViewModel = hiltViewModel(),
    isExpandedScreen: Boolean,
    onCancelClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onCancelClick) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                        )
                    }
                },
                title = { Text(text = "New Card") },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = "",
                        )
                    }

                }
            )
        }
    ) {
        CreateCardContent(viewModel)
    }
}

@Composable
fun CreateCardContent(viewModel: CardViewModel) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            val boardList = mapOf(
                DefaultTaskBoardBGColor to "Praxis",
                SecondaryColor to "Discord Clone",
                LabelOrange to "Trello Workspace",
            )

            val visibilityList = mapOf(
                "ToDo Items" to "",
                "Doing" to "",
                "Done" to "",
            )


            CreateBoardDropDown(
                headingText = "Board",
                contentMap = boardList,
            )

            CreateFormDropDown(
                headingText = "List",
                contentMap = visibilityList,
            )
        }

        CardInfoBox(viewModel)
    }

}

@Composable
fun CardInfoBox(viewModel: CardViewModel) {
    var textCardName by remember { mutableStateOf(TextFieldValue("")) }
    var textDescription by remember { mutableStateOf(TextFieldValue("")) }
    val dialogState = rememberMaterialDialogState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(DefaultTaskBoardBGColor),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
                .background(MaterialTheme.colors.background)
        ) {

            Column {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    value = textCardName,
                    onValueChange = {
                        textCardName = it
                    },
                    label = { Text(text = "Card Name") },
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = SecondaryColor,
                        focusedIndicatorColor = SecondaryColor,
                        unfocusedLabelColor = MaterialTheme.colors.onBackground,
                        focusedLabelColor = SecondaryColor,
                        backgroundColor = Color.Transparent
                    )
                )

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    value = textDescription,
                    onValueChange = {
                        textDescription = it
                    },
                    label = { Text(text = "Description") },
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = SecondaryColor,
                        focusedIndicatorColor = SecondaryColor,
                        unfocusedLabelColor = MaterialTheme.colors.onBackground,
                        focusedLabelColor = SecondaryColor,
                        backgroundColor = Color.Transparent
                    )
                )

                TimeItemRow(
                    modifier = Modifier
                        .padding(16.dp),
                    icon = R.drawable.ic_time,
                    topText = viewModel.startDateText.value,
                    bottomText = viewModel.dueDateText.value,
                    showUpperDivider = false,
                    onStartDateClick = {
                        viewModel.isTopText.value = true
                        viewModel.isBottomText.value = false
                        dialogState.show()
                    },
                    onDueDateClick = {
                        viewModel.isBottomText.value = true
                        viewModel.isTopText.value = false
                        dialogState.show()
                    }
                ) {
                    MaterialDialog(
                        dialogState = dialogState,
                        buttons = {
                            positiveButton("Ok")
                            negativeButton("Cancel")
                        }
                    ) {
                        datepicker {
                            // Do something with the date
                            if (viewModel.isTopText.value) viewModel.startDateText.value =
                                "Starts on ${it.dayOfMonth} ${
                                    (it.month).toString().lowercase()
                                }, ${it.year}"
                            if (viewModel.isBottomText.value) viewModel.dueDateText.value =
                                "Due on ${it.dayOfMonth} ${
                                    (it.month).toString().lowercase()
                                }, ${it.year}"
                        }
                    }
                }

                ItemRow(
                    leadingIcon = {
                        Icon(
                            modifier = Modifier.padding(16.dp),
                            painter = painterResource(id = R.drawable.ic_attachment),
                            contentDescription = "Leading Icon"
                        )
                    },
                    text = "ATTACHMENTS",
                    trailingIcon = Icons.Default.Add,
                    onClick = {

                    }
                )
            }

        }
    }
}
