package com.jetapps.jettaskboard.carddetailscomponents.components

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.shouldShowRationale
import com.jetapps.jettaskboard.CardViewModel

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ImageAttachments(
    viewModel: CardViewModel,
    context: Context,
    galleryPermissionStatus: PermissionState,
    imageUri: Uri?
) {
    when (galleryPermissionStatus.status) {
        PermissionStatus.Granted -> {
            imageUri?.let {
                if (!(viewModel.imageAttachmentList.contains(it))) {
                    viewModel.imageAttachmentList.add(it)
                }
            }

            if (viewModel.imageAttachmentList.isNotEmpty()) {
                FlowRow {
                    viewModel.imageAttachmentList.forEach { image ->
                        Surface(
                            modifier = Modifier
                                .padding(16.dp)
                                .size(150.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Image(
                                bitmap = ImageDecoder.decodeBitmap(
                                    ImageDecoder.createSource(
                                        context.contentResolver,
                                        image
                                    )
                                ).asImageBitmap(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }

        is PermissionStatus.Denied -> {
            if (galleryPermissionStatus.status.shouldShowRationale) {
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
                        Button(
                            onClick = {
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
}
