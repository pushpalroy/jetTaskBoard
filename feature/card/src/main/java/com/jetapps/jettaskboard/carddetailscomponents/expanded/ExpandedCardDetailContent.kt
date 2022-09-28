package com.jetapps.jettaskboard.carddetailscomponents.expanded

import android.Manifest
import android.app.Activity
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane
import com.google.accompanist.adaptive.calculateDisplayFeatures
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.jetapps.jettaskboard.CardViewModel
import com.jetapps.jettaskboard.carddetailscomponents.EditTextCard
import com.jetapps.jettaskboard.carddetailscomponents.ImageAttachments
import com.jetapps.jettaskboard.carddetailscomponents.ItemRow
import com.jetapps.jettaskboard.carddetailscomponents.LabelRow
import com.jetapps.jettaskboard.carddetailscomponents.QuickActionsCard
import com.jetapps.jettaskboard.carddetailscomponents.TimeItemRow
import com.jetapps.jettaskboard.feature.card.R
import com.jetapps.jettaskboard.uimodel.CardDetail
import com.squaredem.composecalendar.ComposeCalendar
import java.time.LocalDate

@Composable
fun ExpandedCardDetailContent(
    leftScrollState: ScrollState,
    rightScrollState: ScrollState,
    cardDetails: CardDetail,
    viewModel: CardViewModel
) {
    val context = LocalContext.current
    TwoPane(
        first = {
            LeftPane(leftScrollState, cardDetails)
        },
        second = {
            RightPane(rightScrollState, cardDetails, viewModel, context)
        },
        strategy = { density, layoutDirection, layoutCoordinates ->
            HorizontalTwoPaneStrategy(
                splitFraction = .5f
            ).calculateSplitResult(density, layoutDirection, layoutCoordinates)
        },
        displayFeatures = calculateDisplayFeatures(activity = context as Activity)
    )

}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RightPane(
    rightScrollState: ScrollState,
    cardDetails: CardDetail,
    viewModel: CardViewModel,
    context: Context
) {

    val galleryPermissionStatus =
        rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)

    var imageUri: Uri? by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rightScrollState)
            .padding(16.dp)
    ) {

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

        LabelRow()

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
                        if (isBottomText.value) dueDateText.value = "Due on ${it.dayOfMonth} ${
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

@Composable
fun LeftPane(leftScrollState: ScrollState, cardDetails: CardDetail) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.999f)
                .verticalScroll(leftScrollState)
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

            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))

            EditTextCard(description = cardDetails.description, isExpanded = true)

            Divider()
            Spacer(modifier = Modifier.height(8.dp))

            QuickActionsCard(isExpanded = true)

            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
        }
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
    }

}