package com.jetapps.jettaskboard.carddetailscomponents

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.jetapps.jettaskboard.CardViewModel
import com.jetapps.jettaskboard.carddetailscomponents.components.EditTextCard
import com.jetapps.jettaskboard.carddetailscomponents.components.ImageAttachments
import com.jetapps.jettaskboard.carddetailscomponents.components.ItemRow
import com.jetapps.jettaskboard.carddetailscomponents.components.LabelRow
import com.jetapps.jettaskboard.carddetailscomponents.components.QuickActionsCard
import com.jetapps.jettaskboard.carddetailscomponents.components.TimeItemRow
import com.jetapps.jettaskboard.feature.card.R
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CardDetailsMainPane(
    modifier: Modifier = Modifier,
    viewModel: CardViewModel,
    context: Context = LocalContext.current,
    configuration: Configuration = LocalConfiguration.current,
    launcher: ManagedActivityResultLauncher<String, Uri?>,
    scrollState: ScrollState,
    galleryPermissionState: PermissionState,
    imageUri: Uri? = null,
    dialogState: MaterialDialogState,
    isExpandedScreen: Boolean
) {
    if (isExpandedScreen) {
        Column(
            modifier = modifier
                .fillMaxWidth(0.999f)
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.backlog),
                contentDescription = "Backlog",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            androidx.compose.material.Text(
                modifier = Modifier.padding(16.dp),
                text = viewModel.cardModel.title ?: "Backlog",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            androidx.compose.material.Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "${(viewModel.cardModel.boardName) ?: "Praxis"} in list ${(viewModel.cardModel.listName) ?: "Backlog"}",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))

            EditTextCard(
                viewModel = viewModel,
                isExpanded = true,
                iconResource = R.drawable.ic_notes,
                placeHolderText = "Add a description",
                onValue = viewModel.cardModel.description ?: "Description..",
                onValueChanged = { newCharSets ->
                    viewModel.cardModel = viewModel.cardModel.copy(
                        description = newCharSets
                    )
                }
            )

            Divider()
            Spacer(modifier = Modifier.height(8.dp))

            EditTextCard(
                viewModel = viewModel,
                isExpanded = true,
                iconResource = R.drawable.ic_notes,
                placeHolderText = "Add a Title",
                onValue = viewModel.cardModel.title ?: "Title..",
                onValueChanged = { newCharSets ->
                    viewModel.cardModel = viewModel.cardModel.copy(
                        title = newCharSets
                    )
                }
            )

            Divider()

            Spacer(modifier = Modifier.height(8.dp))

            QuickActionsCard(isExpanded = true)

            Spacer(modifier = Modifier.height(8.dp))

            Divider()

            Spacer(modifier = Modifier.height(8.dp))
        }
    } else {
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = viewModel.cardModel.title ?: "Backlog",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "${(viewModel.cardModel.boardName) ?: "Praxis"} in list ${(viewModel.cardModel.listName) ?: "Backlog"}",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Divider()

            QuickActionsCard(false)

            Divider()

            EditTextCard(
                viewModel = viewModel,
                isExpanded = true,
                iconResource = R.drawable.ic_notes,
                placeHolderText = "Add a description",
                onValue = viewModel.cardModel.description ?: "Description..",
                onValueChanged = { newCharSets ->
                    viewModel.cardModel = viewModel.cardModel.copy(
                        description = newCharSets
                    )
                }
            )

            Divider()

            Spacer(modifier = Modifier.height(8.dp))

            EditTextCard(
                viewModel = viewModel,
                isExpanded = true,
                iconResource = R.drawable.ic_notes,
                placeHolderText = "Add a Title",
                onValue = viewModel.cardModel.title ?: "Title..",
                onValueChanged = { newCharSets ->
                    viewModel.cardModel = viewModel.cardModel.copy(
                        title = newCharSets
                    )
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            LabelRow(viewModel)

            val members by rememberSaveable {
                mutableStateOf(
                    viewModel.cardModel.authorName ?: "Members..."
                )
            }
            ItemRow(
                leadingIcon = {
                    Icon(
                        modifier = Modifier.padding(16.dp),
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Leading Icon"
                    )
                },
                text = members
            )
            TimeItemRow(
                modifier = Modifier.testTag("time_item_row"),
                icon = R.drawable.ic_time,
                topText = viewModel.startDateText.value,
                bottomText = viewModel.dueDateText.value,
                onStartDateClick = {
                    viewModel.isTopText.value = true
                    viewModel.isBottomText.value = false
                    dialogState.show()
                },
                onDueDateClick = {
                    viewModel.isBottomText.value = true
                    viewModel.isTopText.value = false
                    dialogState.show()
                },
            ) {
                MaterialDialog(
                    dialogState = dialogState,
                    buttons = {
                        positiveButton("Ok")
                        negativeButton("Cancel")
                    }
                ) {
                    // TODO(Niket): Implement the material official ate-picker composable
//                datepicker {
//                    // Do something with the date
//                    if (viewModel.isTopText.value) viewModel.startDateText.value =
//                        "Starts on ${it.dayOfMonth} ${
//                        (it.month).toString().lowercase()
//                        }, ${it.year}"
//                    if (viewModel.isBottomText.value) viewModel.dueDateText.value =
//                        "Due on ${it.dayOfMonth} ${
//                        (it.month).toString().lowercase()
//                        }, ${it.year}"
//                }
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
                    if (galleryPermissionState.status != PermissionStatus.Granted) {
                        galleryPermissionState.launchPermissionRequest()
                    } else {
                        launcher.launch("image/*")
                    }
                }
            )

            ImageAttachments(viewModel, context, galleryPermissionState, imageUri)

            Divider()

            when (configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> {
                    Spacer(modifier = Modifier.height(400.dp))
                }

                else -> {
                }
            }
        }
    }
}