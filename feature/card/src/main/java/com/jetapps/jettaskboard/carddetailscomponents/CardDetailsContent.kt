package com.jetapps.jettaskboard.carddetailscomponents

import android.Manifest
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.jetapps.jettaskboard.feature.card.R
import com.jetapps.jettaskboard.uimodel.CardDetail
import com.squaredem.composecalendar.ComposeCalendar
import java.time.LocalDate

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CardDetailsContent(
    scrollState: ScrollState,
    isExpandedScreen: Boolean,
    cardDetails: CardDetail
) {

    val configuration = LocalConfiguration.current

    var imageUri: Uri? by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    val attachmentsList = remember { mutableStateListOf<Uri>() }

    val galleryPermissionStatus =
        rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)
    var onAttachmentClick by remember { mutableStateOf(false) }

    var isPermissionGranted = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        when (galleryPermissionStatus.status) {
            PermissionStatus.Granted -> {
                isPermissionGranted.value = true
            }

            is PermissionStatus.Denied -> {
                if (galleryPermissionStatus.status.shouldShowRationale) {
                    isPermissionGranted.value = false
                }
            }
        }
    }

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
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.height(16.dp))

        Divider()

        QuickActionsCard(false)

        Divider()

        EditTextCard(description = cardDetails.description)

        LabelRow()

        val members by remember { mutableStateOf(cardDetails.authorName ?: "Members...") }
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

        val showCalendar = rememberSaveable { mutableStateOf(false) }
        val isTopText = rememberSaveable { mutableStateOf(false) }
        val isBottomText = rememberSaveable { mutableStateOf(false) }

        val startDateText = rememberSaveable {
            mutableStateOf(cardDetails.startDate ?: "Start Date...")
        }

        val dueDateText = rememberSaveable {
            mutableStateOf(cardDetails.dueDate ?: "Due Date...")
        }

        TimeItemRow(
            modifier = Modifier,
            icon = R.drawable.ic_time,
            topText = startDateText.value,
            bottomText = dueDateText.value,
            onStartDateClick = {
                showCalendar.value = !showCalendar.value
                isTopText.value = true
                isBottomText.value = false
            },
            onDueDateClick = {
                showCalendar.value = !showCalendar.value
                isBottomText.value = true
                isTopText.value = false
            }
        ) {
            if (showCalendar.value) {
                ComposeCalendar(
                    onDone = { it: LocalDate ->
                        // Hide dialog
                        showCalendar.value = false
                        // Do something with the date
                        if (isTopText.value) startDateText.value =
                            "Starts on ${it.dayOfMonth} ${
                                (it.month).toString().lowercase()
                            }, ${it.year}"
                        if (isBottomText.value) dueDateText.value =
                            "Due on ${it.dayOfMonth} ${
                                (it.month).toString().lowercase()
                            }, ${it.year}"
                    },
                    onDismiss = {
                        // Hide dialog
                        showCalendar.value = false
                    }
                )
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
                galleryPermissionStatus.launchPermissionRequest()
                onAttachmentClick = true
                if (galleryPermissionStatus.status == PermissionStatus.Granted) {
                    launcher.launch("image/*")
                }
            }
        )


        if (isPermissionGranted.value) {

            imageUri?.let {
                if (!(attachmentsList.contains(it))) {
                    attachmentsList.add(it)
                }
            }

            if (attachmentsList.isNotEmpty()) {
                FlowRow {
                    attachmentsList.forEach { image ->
                        Image(
                            modifier = Modifier.padding(16.dp).size(150.dp),
                            bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, image)).asImageBitmap(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        } else {
            AlertDialog(
                onDismissRequest = {},
                title = {
                    Text(
                        text = "Permission Request",
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.h6.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                text = {
                    Text("To use this app's functionalities, you need to give us the permission.")
                },
                confirmButton = {
                    Button(onClick = {
                        galleryPermissionStatus.launchPermissionRequest()
                    }
                    ) {
                        Text("Give Permission")
                    }
                }
            )
        }
    }

}