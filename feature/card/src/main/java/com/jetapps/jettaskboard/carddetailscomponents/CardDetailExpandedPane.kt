@file:OptIn(ExperimentalPermissionsApi::class)

package com.jetapps.jettaskboard.carddetailscomponents

import android.content.Context
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.jetapps.jettaskboard.CardViewModel
import com.jetapps.jettaskboard.carddetailscomponents.components.ImageAttachments
import com.jetapps.jettaskboard.carddetailscomponents.components.ItemRow
import com.jetapps.jettaskboard.carddetailscomponents.components.LabelRow
import com.jetapps.jettaskboard.carddetailscomponents.components.TimeItemRow
import com.jetapps.jettaskboard.feature.card.R
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState

@Composable
fun CardDetailExpandedPane(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    viewModel: CardViewModel,
    dialogState: MaterialDialogState,
    galleryPermissionStatus: PermissionState,
    context: Context = LocalContext.current,
    launcher: ManagedActivityResultLauncher<String, Uri?>,
    imageUri: Uri? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        val members by remember {
            mutableStateOf(
                viewModel.cardModel.authorName ?: "Members...",
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

        LabelRow(viewModel)

        TimeItemRow(
            modifier = Modifier,
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
            }
        ) {
            MaterialDialog(
                dialogState = dialogState,
                buttons = {
                    positiveButton("Ok")
                    negativeButton("Cancel")
                }
            ) {
                // Todo(Niket) : implement the official material date picker
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
                if (galleryPermissionStatus.status != PermissionStatus.Granted) {
                    galleryPermissionStatus.launchPermissionRequest()
                } else {
                    launcher.launch("image/*")
                }
            }
        )

        ImageAttachments(viewModel, context, galleryPermissionStatus, imageUri)

        Divider()
    }
}