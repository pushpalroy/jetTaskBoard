package com.jetapps.jettaskboard.carddetailscomponents

import android.Manifest
import android.content.res.Configuration
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.jetapps.jettaskboard.CardViewModel
import com.jetapps.jettaskboard.feature.card.R
import com.jetapps.jettaskboard.uimodel.CardDetail
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CardDetailsContent(
    scrollState: ScrollState,
    cardDetails: CardDetail,
    viewModel: CardViewModel
) {
    val configuration = LocalConfiguration.current

    var imageUri: Uri? by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    val galleryPermissionStatus =
        rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)

    val dialogState = rememberMaterialDialogState()

    Column(modifier = Modifier.verticalScroll(scrollState)) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = cardDetails.title ?: "Backlog",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "${(cardDetails.boardName) ?: "Praxis"} in list ${(cardDetails.listName) ?: "Backlog"}",
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        Divider()

        QuickActionsCard(false)

        Divider()

        EditTextCard(viewModel = viewModel)

        LabelRow(viewModel)

        val members by rememberSaveable { mutableStateOf(cardDetails.authorName ?: "Members...") }
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
                if (galleryPermissionStatus.status != PermissionStatus.Granted) {
                    galleryPermissionStatus.launchPermissionRequest()
                } else {
                    launcher.launch("image/*")
                }
            }
        )

        ImageAttachments(viewModel, context, galleryPermissionStatus, imageUri)

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
