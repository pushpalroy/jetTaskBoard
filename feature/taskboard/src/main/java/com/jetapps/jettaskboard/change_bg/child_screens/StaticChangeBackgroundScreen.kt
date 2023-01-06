package com.jetapps.jettaskboard.change_bg.child_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetapps.jettaskboard.change_bg.ChangeBackgroundScreenState
import com.jetapps.jettaskboard.component.StaticImagePlaceHolderCard
import com.jetapps.jettaskboard.feature.taskboard.R

@Composable
fun StaticChangeBackgroundScreen(
    onImageTypeSelected: (ChangeBackgroundScreenState) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(0.9f),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Divider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StaticImagePlaceHolderCard(
                modifier = Modifier.weight(1f),
                onImageClicked = {
                    onImageTypeSelected(
                        ChangeBackgroundScreenState.PHOTO_SCREEN
                    )
                },
                subTitle = "Photos",
                drawableId = R.drawable.photo_collection_img
            )

            Spacer(modifier = Modifier.width(12.dp))

            StaticImagePlaceHolderCard(
                modifier = Modifier.weight(1f),
                onImageClicked = {
                    onImageTypeSelected(
                        ChangeBackgroundScreenState.COLORS_SCREEN
                    )
                },
                subTitle = "Colors",
                drawableId = R.drawable.color_collection_img
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Divider()

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .padding(start = 8.dp, top = 4.dp, bottom = 4.dp)
                .align(Alignment.Start),
            text = "Custom",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colors.onBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    imageVector = Icons.Default.Add,
                    tint = Color.Black,
                    contentDescription = "Add Custom Image Background Icon"
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            StaticImagePlaceHolderCard(
                modifier = Modifier.weight(1f),
                onImageClicked = {},
                subTitle = "",
                drawableId = R.drawable.bg_board
            )
        }
    }
}